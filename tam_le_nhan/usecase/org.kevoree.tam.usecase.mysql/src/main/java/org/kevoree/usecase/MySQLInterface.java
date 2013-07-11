package org.kevoree.usecase;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 11/07/13
 * Time: 14:52
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public interface MySQLInterface {
    ResultSet query(String query) throws SQLException;
}
