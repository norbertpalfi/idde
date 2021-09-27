package edu.bbte.quiz.backend.dao.jdbc;

import edu.bbte.quiz.backend.dao.DaoException;
import edu.bbte.quiz.backend.dao.OwnerDao;
import edu.bbte.quiz.backend.model.Owner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OwnerJdbcDao implements OwnerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerJdbcDao.class);
    private boolean logQueries = DataSourceFactory.getLogQueries();
    private boolean logUpdates = DataSourceFactory.getLogUpdates();
    private Integer nrOfReads = 0;
    private Integer nrOfWrites = 0;

    public Integer getNrOfReads() {
        return nrOfReads;
    }

    public Integer getNrOfWrites() {
        return nrOfWrites;
    }

    @Override
    public Collection<Owner> selectAll() {
        Collection<Owner> collection = new ArrayList<>();
        Statement statement;
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("Select id, kilometers, horsepower, ownerName, "
                    + "carModel, startDate from Cars");
            while (result.next()) {
                Owner owner = new Owner();
                owner.setId(result.getInt("id"));
                owner.setName(result.getString("name"));
                owner.setCity(result.getString("city"));
                collection.add(owner);
            }
            if(logQueries) {
                LOGGER.info("Selected all");
                nrOfReads++;
            }
            return collection;
        } catch (SQLException e) {
            LOGGER.error("Could not select.", e);
            throw new DaoException("Could not select.", e);
        }
    }

    @Override
    public Owner select(Integer id) {
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, kilometers, "
                    + "horsepower, ownerName, carModel, startDate FROM Owners WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Owner owner = new Owner();
            owner.setId(resultSet.getInt(1));
            owner.setName(resultSet.getString(2));
            owner.setCity(resultSet.getString(3));
            if(logQueries) {
                LOGGER.info("Selected {}",id);
                nrOfReads++;
            }
            return owner;
        } catch (SQLException e) {
            throw new DaoException("Error when finding Owner by ID: " + id, e);
        }
    }

    @Override
    public void create(Owner entity) {
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Owners ("
                    + "name, city) "
                    + "VALUES (?, ?, ?)");
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getCity());
            preparedStatement.executeUpdate();
            if(logUpdates) {
                LOGGER.info("Created {}",entity.toString());
                nrOfWrites++;
            }
        } catch (SQLException e) {
            throw new DaoException("Error when creating Owner by ID: " + entity.getId(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Owners "
                    + "WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            if(logUpdates) {
                LOGGER.info("deleted {}",id);
                nrOfWrites++;
            }
        } catch (SQLException e) {
            throw new DaoException("Error when deleting Owner by ID: " + id, e);
        }

    }

    @Override
    public void update(Owner entity) {
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Owners SET "
                    + "name = ?, "
                    + "city = ?, "
                    + "WHERE id = ?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();

            if(logUpdates) {
                LOGGER.info("Updated {}",entity.toString());
                nrOfWrites++;
            }

        } catch (SQLException e) {
            throw new DaoException("Error when updating Owner by ID: " + entity.getId(), e);
        }
    }

    @Override
    public Collection<Owner> selectByName(String name) {
        throw new DaoException("Not implemented");
    }

    @Override
    public Owner selectByCarModel(String carModel) {
        throw new DaoException("Not implemented");
    }
}
