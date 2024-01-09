package ba.unsa.etf.rpr.DAL.DTO;

public record LoginRequest (int id, String username,String password, int roleId) {
}
