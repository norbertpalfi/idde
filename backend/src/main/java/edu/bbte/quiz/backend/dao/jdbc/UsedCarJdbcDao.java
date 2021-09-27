package edu.bbte.quiz.backend.dao.jdbc;

import edu.bbte.quiz.backend.dao.DaoException;
import edu.bbte.quiz.backend.dao.UsedCarDao;
import edu.bbte.quiz.backend.model.UsedCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UsedCarJdbcDao implements UsedCarDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarJdbcDao.class);

    @Override
    public Collection<UsedCar> selectAll() {
        Collection<UsedCar> collection = new ArrayList<>();
        Statement statement = null;
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("Select id, kilometers, horsepower, ownerId, "
                    + "carModel, startDate from Cars");
            while (result.next()) {
                UsedCar usedCar = new UsedCar();
                usedCar.setId(result.getInt("id"));
                usedCar.setKilometers(result.getInt("kilometers"));
                usedCar.setHorsePower(result.getInt("horsePower"));
                //usedCar.setOwnerId(result.getInt("ownerId"));
                usedCar.setCarModel(result.getString("carModel"));
                usedCar.setStartDate(result.getInt("startDate"));

                collection.add(usedCar);
            }
            return collection;
        } catch (SQLException e) {
            LOGGER.error("Could not select.", e);
            throw new DaoException("Could not select.", e);
        }
    }

    @Override
    public UsedCar select(Integer id) {
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cars "
                    + "WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            UsedCar usedCar = new UsedCar();
            createItem(usedCar,resultSet);
            return usedCar;
        } catch (SQLException e) {
            throw new DaoException("Error when finding Owner by ID: " + id, e);
        }
    }

    private void createItem(UsedCar usedCar, ResultSet resultSet) throws SQLException {
        usedCar.setId(resultSet.getInt(1));
        usedCar.setKilometers(resultSet.getInt(2));
        usedCar.setHorsePower(resultSet.getInt(3));
        //usedCar.setOwnerId(resultSet.getInt(4));
        usedCar.setCarModel(resultSet.getString(5));
        usedCar.setStartDate(resultSet.getInt(6));
    }

    @Override
    public void create(UsedCar entity) {
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Cars (kilometers, "
                    + "horsePower, ownerId, carModel, startDate) VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, entity.getKilometers());
            preparedStatement.setInt(2, entity.getHorsePower());
            preparedStatement.setInt(3, entity.getOwner().getId());
            preparedStatement.setString(4, entity.getCarModel());
            preparedStatement.setInt(5, entity.getStartDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error when finding Owner by ID: " + entity.getId(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Cars "
                    + "WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error when deleting Owner by ID: " + id, e);
        }
    }

    @Override
    public void update(UsedCar entity) {
        try (Connection connection = DataSourceFactory.getHikariDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Owners SET "
                    + "kilometers = ?, "
                    + "horsePower = ?, "
                    + "ownerId = ?, "
                    + "carModel = ?, "
                    + "startDate = ?, "
                    + "WHERE id = ?");
            preparedStatement.setInt(1, entity.getKilometers());
            preparedStatement.setInt(2, entity.getHorsePower());
            preparedStatement.setInt(3, entity.getOwner().getId());
            preparedStatement.setString(4, entity.getCarModel());
            preparedStatement.setInt(5, entity.getStartDate());
            preparedStatement.setInt(6, entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error when updating Owner by ID: " + entity.getId(), e);
        }
    }

    @Override
    public Collection<UsedCar> selectByName(String name) {
        throw new DaoException("Not implemented");
    }

    @Override
    public UsedCar selectByOwnerName(String name) {
        throw new DaoException("Not implemented");
    }
}
