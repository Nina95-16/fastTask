package com.example.fastTask.manager;


import com.example.fastTask.db.DBConnectionProvider;
import com.example.fastTask.model.Task;
import com.example.fastTask.model.TaskStatus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private Connection connection = DBConnectionProvider.getProvider().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
    private SimpleDateFormat sdfTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private UserManager userManager = new UserManager();

    public Task getById(int id) {
        String sql = "SELECT * FROM task WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getTaskFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public boolean createTask(Task task) {
        String sql = "INSERT INTO task(title,description,status,deadline,user_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, task.getTitle());
            pStatement.setString(2, task.getDescription());
            pStatement.setString(3, task.getTaskStatus().name());
            if (task.getDeadline() != null) {
                pStatement.setString(4, sdf.format(task.getDeadline()));
            } else
                pStatement.setString(4, null);
            pStatement.setInt(5, task.getUser().getId());
            pStatement.executeUpdate();
            ResultSet rs = pStatement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }


    private Task getTaskFromResultSet(ResultSet resultSet) {
        try {
            return Task.builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .deadline(resultSet.getString(4) == null ? null : sdf.parse(resultSet.getString(4)))
                    .createdDate(resultSet.getString(5) == null ? null : sdfTimestamp.parse(resultSet.getString(5)))
                    .taskStatus(TaskStatus.valueOf(resultSet.getString(6)))
                    .user(userManager.getById(resultSet.getInt(7)))
                    .build();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        String sql = "SELECT * FROM task ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getAllTasksByUserIdAndStatus(int userId, TaskStatus status) {
        List<Task> tasks = new ArrayList<Task>();
        String sql = "SELECT * FROM task WHERE user_id = ? AND status =  ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, status.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getAllTasksByUser(int userId) {
        List<Task> tasks = new ArrayList<Task>();
        String sql = "SELECT * FROM task WHERE user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }


    public boolean update(int id, TaskStatus status) {
        String sql = "UPDATE task SET status = '" + status.name() + "' WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM task WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
