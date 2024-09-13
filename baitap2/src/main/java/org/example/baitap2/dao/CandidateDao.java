package org.example.baitap2.dao;

import org.example.baitap2.models.Candidates;
import org.example.baitap2.models.Skills;
import org.example.baitap2.utils.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class CandidateDao {
    public void addCandidateWithSkills(Candidates candidate, List<Skills> skills) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement pstmtCandidate = null;
        PreparedStatement pstmtSkills = null;

        try {
            connection.setAutoCommit(false); // Begin transaction

            // Insert into candidates table
            String insertCandidateSQL = "INSERT INTO candidates (firstName, lastName, dob, phone, email) VALUES (?, ?, ?, ?, ?)";
            pstmtCandidate = connection.prepareStatement(insertCandidateSQL, Statement.RETURN_GENERATED_KEYS);
            pstmtCandidate.setString(1, candidate.getFirstName());
            pstmtCandidate.setString(2, candidate.getLastName());
            pstmtCandidate.setDate(3, new java.sql.Date(candidate.getDob().getTime()));
            pstmtCandidate.setString(4, candidate.getPhone());
            pstmtCandidate.setString(5, candidate.getEmail());
            pstmtCandidate.executeUpdate();

            // Retrieve the generated candidate ID
            int candidateId = -1;
            try (ResultSet rs = pstmtCandidate.getGeneratedKeys()) {
                if (rs.next()) {
                    candidateId = rs.getInt(1);
                }
            }

            if (candidateId == -1) {
                throw new SQLException("Failed to retrieve candidate ID");
            }

            // Insert into candidate_skills table
            String insertSkillSQL = "INSERT INTO candidate_skills (candidate_id, skill_id) VALUES (?, ?)";
            pstmtSkills = connection.prepareStatement(insertSkillSQL);
            for (Skills skill : skills) {
                pstmtSkills.setInt(1, candidateId);
                pstmtSkills.setInt(2, skill.getId());
                pstmtSkills.addBatch();
            }
            pstmtSkills.executeBatch();

            // Commit transaction
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback on error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            throw e; // Re-throw exception after rollback
        } finally {
            // Close resources
            if (pstmtCandidate != null) pstmtCandidate.close();
            if (pstmtSkills != null) pstmtSkills.close();
            if (connection != null) connection.close();
        }
    }
}
