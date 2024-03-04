package com.example.ex_3.DataAccess;

import com.example.ex_3.Connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.table.DefaultTableModel;

/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 */
public class AbstractDAO<T> {
    /**
     * The constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    /**
     * The Type.
     */
    final Class<T> type;

    /**
     * Instantiates a new Abstract dao.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String selectAll() {
        return "SELECT " + " * " + " FROM " + type.getSimpleName();
    }

    private String createSelectQuery(String field) {
        return "SELECT " + " * " + " FROM " + type.getSimpleName() + " WHERE " + field + " =?";
    }

    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES(");
        for (Field field : type.getDeclaredFields()) {
            sb.append("?, ");
        }
        sb.replace(sb.length() - 2, sb.length(), " )");
        return sb.toString();
    }

    /**
     * Create delete query string.
     *
     * @param field the field
     * @return the string
     */
    String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Create update query string.
     *
     * @param changeField    the change field
     * @param conditionField the condition field
     * @return the string
     */
    String createUpdateQuery(String changeField, String conditionField) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        sb.append(changeField).append(" =?");
        sb.append("WHERE ").append(conditionField).append(" =?");
        return sb.toString();
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = selectAll();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:selectAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * `Insert` object int.
     *
     * @param t the t
     * @return the int
     */
    public int insertObject(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int k = 1;
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                statement.setObject(k, field.get(t));
                k++;
            }
            System.out.println(statement);
            statement.executeUpdate();
            resultSet = 1;
            return resultSet;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insertObject " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst())
                return null;
            else
                return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Delete by id int.
     *
     * @param id the id
     * @return the int
     */
    public int deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeUpdate();
            return resultSet;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IntrospectionException | SQLException | InvocationTargetException |
                 IllegalArgumentException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Update field int.
     *
     * @param changeField    the change field
     * @param changeValue    the change value
     * @param conditionField the condition field
     * @param conditionValue the condition value
     * @return the int
     */
    public int updateField(String changeField, String changeValue, String conditionField, String conditionValue) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createUpdateQuery(changeField, conditionField);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, changeValue);
            statement.setString(2, conditionValue);
            resultSet = statement.executeUpdate();
            return resultSet;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:updateField " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }


    /**
     * Delete by field int.
     *
     * @param field the field
     * @param name  the name
     * @return the int
     */
    public int deleteByField(String field, String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createDeleteQuery(field);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeUpdate();
            return resultSet;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Create table default table model.
     *
     * @param list the list
     * @return the default table model
     * @throws IllegalAccessException the illegal access exception
     */
    public DefaultTableModel createTable(List<T> list) throws IllegalAccessException {
        T object = list.get(0);
        String[] column = new String[object.getClass().getDeclaredFields().length];
        Object[][] data = new Object[list.size()][object.getClass().getDeclaredFields().length];
        int index = 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            column[index++] = field.getName();
        }
        for(int i=0;i<list.size();i++){
            Object aux = list.get(i);
            int k=0;
            for(Field field: aux.getClass().getDeclaredFields()){
                field.setAccessible(true);
                data[i][k++]=  field.get(aux);
            }
        }
        return new DefaultTableModel(data, column);
    }
}
