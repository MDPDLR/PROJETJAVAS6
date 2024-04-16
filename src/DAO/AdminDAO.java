package DAO;

public interface AdminDAO {
    void insertAdmin(Admin admin);
    void updateAdmin(Admin admin);
    Admin getAdminByEmail(String email);
}
