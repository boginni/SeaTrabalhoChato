package head;

public class Table {

    String[][] tableContent;
    int rowCount;
    int columCont;



    public Table(String table) {
        String[] rows = table.split("\n");
        rowCount = rows.length;
        columCont = rows[0].split("\t").length;
        tableContent = new String[rowCount][columCont];
        for (int i = 0; i < rows.length; i++) {
            tableContent[i] = rows[i].split("\t");
        }

    }


    public String getCell(int row, int colum){
        return tableContent[row][colum];
    }
    
    public void setCell(int row, int colum, String newCellStr){
        try {
            tableContent[row][colum] = newCellStr;
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Failed to copy");
        }
    }

    public String[] getRow(int row){
        return tableContent[row];
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumCont() {
        return columCont;
    }
}
