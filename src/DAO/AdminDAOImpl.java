package DAO;

public class AdminDAOImpl implements AdminDAO {
    private Connection connection;

    // Constructeur prenant une connexion à la base de données
    public AdminDAOImpl(Connection connection) {
        this.connection = connection;
    }

   @Override
public void insertAdmin(Admin admin) {
    String query = "INSERT INTO Admin (Nom, IDemploye, mdpEmploye) VALUES (?, ?, ?)";
    
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, admin.getNom());
        statement.setInt(2, admin.getIdEmploye());
        statement.setString(3, admin.getMdpEmploye());
        
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Nouvel administrateur inséré avec succès.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
public void updateAdmin(Admin admin) {
    String query = "UPDATE Admin SET Nom = ?, mdpEmploye = ? WHERE IDemploye = ?";
    
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, admin.getNom());
        statement.setString(2, admin.getMdpEmploye());
        statement.setInt(3, admin.getIdEmploye());
        
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Informations de l'administrateur mises à jour avec succès.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   
@Override
public Admin getAdminById(int idEmploye) {
    String query = "SELECT * FROM Admin WHERE IDemploye = ?";
    
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, idEmploye);
        
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String nom = resultSet.getString("Nom");
            String mdpEmploye = resultSet.getString("mdpEmploye");
            
            return new Admin(idEmploye, nom, mdpEmploye);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return null;
}

}
