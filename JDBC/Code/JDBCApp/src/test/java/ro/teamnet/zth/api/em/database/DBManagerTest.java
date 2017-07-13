package ro.teamnet.zth.api.em.database;

import org.junit.Test;
import ro.teamnet.zth.api.database.DBManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Alexandra.Gaman on 7/13/2017.
 */
public class DBManagerTest {

    @Test
    public void testGetConnection() {
        Connection c = null;
        c = DBManager.getConnection(c);
        assertNotEquals(c, null);
        try {
            assertFalse(c.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCheckConnection() {
        Connection c = null;
        c = DBManager.getConnection(c);
        assertTrue(DBManager.checkConnection(c));
    }
}
