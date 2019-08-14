import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class eventTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	queryManager q;
    int rowCount;
    ArrayList<String> IDArrayList;
    ArrayList<String> nameArrayList;
    ArrayList<String> dateArrayList;
    ArrayList<String> locationArrayList;
    ArrayList<String> rVolArrayList;
    ArrayList<String> participantsArrayList;
    public eventTableModel() throws SQLException {
        rowCount = 0;
        q = queryManager.getQueryManager();
        IDArrayList = new ArrayList<String>();
        nameArrayList = new ArrayList<String>();
        dateArrayList = new ArrayList<String>();
        locationArrayList = new ArrayList<String>();
        rVolArrayList = new ArrayList<String>();
        participantsArrayList = new ArrayList<String>();
        refreshForAll("");

    }
    @Override
    public int getRowCount() {

            return rowCount;

    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        switch(column)
        {
            case 0:
                return "ID";
            case 1:
                return "Name";

            case 2:
                return "Date";

            case 3:
                return "Location";
            case 4:
                return "Req. Volunteers";
            case 5:
                return "Participants";

            default:
                return "OOPS";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0:
                return IDArrayList.get(rowIndex);
            case 1:
                return nameArrayList.get(rowIndex);
            case 2:
                return dateArrayList.get(rowIndex);
            case 3:
                return locationArrayList.get(rowIndex);
            case 4:
                return rVolArrayList.get(rowIndex);
            case 5:
                return participantsArrayList.get(rowIndex);
            default:
                return  null;
        }
    }

    public void refreshForAll(String searched) throws SQLException {
        IDArrayList.clear();
        nameArrayList.clear();
        dateArrayList.clear();
        locationArrayList.clear();
        rVolArrayList.clear();
        participantsArrayList.clear();
        rowCount = q.getEventCount(searched);
        ResultSet rs = q.getEvents(searched);
        while(rs.next())
        {
        	IDArrayList.add(""+rs.getInt(1));
        	nameArrayList.add(rs.getString(2));
        	dateArrayList.add(""+rs.getDate(3));
        	locationArrayList.add(rs.getString(4));
        	rVolArrayList.add(""+rs.getInt(5));
        	participantsArrayList.add(""+rs.getInt(6));
        }
    }

    
    
  
}
