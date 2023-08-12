import java.util.Scanner;

public class SparseMatrix {

    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);

        String m1= scanner.nextLine();
        String m2= scanner.nextLine();
        String operation= scanner.nextLine();

        //SparseMatrix sparseMatrix1= getSparseMatrix(m1);
        //SparseMatrix sparseMatrix2= getSparseMatrix(m2);

        int r1;
        int c1;

        int r2;
        int c2;

        r1= Integer.parseInt( m1.substring(0, m1.indexOf(',')));
        String remain1=  m1.substring( m1.indexOf(',')+1);
        //-------------------------------------------------------------------------------------------------------------
        String elements1="";
        if(remain1.indexOf(',')==-1){
            c1 = Integer.parseInt(remain1);
        }
        else {
            c1 = Integer.parseInt(remain1.substring(0, remain1.indexOf(',')));
            elements1= remain1.substring(remain1.indexOf(',')+1);
        }




        r2= Integer.parseInt( m2.substring(0, m2.indexOf(',')));
        String remain2=  m2.substring( m2.indexOf(',')+1);
        //-------------------------------------------------------------------------------------------------------------
        String elements2="";
        if (remain2.indexOf(',')==-1){
            c2 = Integer.parseInt(remain2);
        }
        else {
            c2 = Integer.parseInt(remain2.substring(0, remain2.indexOf(',')));
            elements2 = remain2.substring(remain2.indexOf(',') + 1);
        }






        //System.out.println(sparseMatrix1);
        //System.out.println(sparseMatrix2);

        if(operation.equals("SUM")) {
            //System.out.println(sparseMatrix1.sum(sparseMatrix2));
            //System.out.println(sparseMatrix1.newSum(sparseMatrix2));
            System.out.println(straightSum(elements1,elements2,r1,c1));
        }
        if (operation.equals("MUL")) {
            IntMatrix sparseMatrix1 = new IntMatrix(r1,c1);
            IntMatrix sparseMatrix2 = new IntMatrix(r2,c2);

            createSparseMatrices(elements1,elements2,sparseMatrix1,sparseMatrix2);

            System.out.println(sparseMatrix1.mul(sparseMatrix2));
        }
    }













    static IntMatrix straightSum(String elements1, String elements2, int row, int column){
        IntMatrix sum= new IntMatrix(row, column);
        while (!elements1.equals("")  || !elements2.equals("")){
            if(!elements1.equals("")) {
                int r = Integer.parseInt(elements1.substring(0, elements1.indexOf(',')));
                String remainder1 = elements1.substring(elements1.indexOf(',') + 1);
                //
                int c = Integer.parseInt(remainder1.substring(0, remainder1.indexOf(',')));
                String remainder2 = remainder1.substring(remainder1.indexOf(',') + 1);
                //
                int value = 0;
                String remainder3 = "";
                //
                if (remainder2.indexOf(',') != -1) {
                    value = Integer.parseInt(remainder2.substring(0, remainder2.indexOf(',')));
                    remainder3 = remainder2.substring(remainder2.indexOf(',') + 1);
                }
                if (remainder2.indexOf(',') == -1) {
                    value = Integer.parseInt(remainder2);
                    remainder3 = "";
                }
                //
                IntNode node = new IntNode(r, c, value);
                if (value!=0){
                    sum.insertNodeInOrder(node,row,column);
                }
                //
                elements1 = remainder3;
            }
            if (!elements2.equals("")){
                int r= Integer.parseInt(elements2.substring(0,elements2.indexOf(',')));
                String remainder1= elements2.substring(elements2.indexOf(',')+1);
                //
                int c= Integer.parseInt(remainder1.substring(0,remainder1.indexOf(',')));
                String remainder2= remainder1.substring(remainder1.indexOf(',')+1);
                //
                int value=0;
                String remainder3="";
                //
                if (remainder2.indexOf(',')!=-1){
                    value= Integer.parseInt(remainder2.substring(0,remainder2.indexOf(',')));
                    remainder3= remainder2.substring(remainder2.indexOf(',')+1);
                }
                if (remainder2.indexOf(',')==-1){
                    value= Integer.parseInt(remainder2);
                    remainder3= "";
                }
                //
                IntNode node= new IntNode(r,c,value);
                if (value!=0){
                    sum.insertNodeInOrder(node,row,column);
                }
                //
                elements2= remainder3;
            }
        }
        return sum;
    }





















    static void createSparseMatrices(String elements1, String elements2, IntMatrix sparseMatrix1, IntMatrix sparseMatrix2){


        while (!elements1.equals("")  || !elements2.equals("")){

            if(!elements1.equals("")) {
                int r = Integer.parseInt(elements1.substring(0, elements1.indexOf(',')));
                String remainder1 = elements1.substring(elements1.indexOf(',') + 1);
                //
                int c = Integer.parseInt(remainder1.substring(0, remainder1.indexOf(',')));
                String remainder2 = remainder1.substring(remainder1.indexOf(',') + 1);
                //
                int value = 0;
                String remainder3 = "";
                //
                if (remainder2.indexOf(',') != -1) {
                    value = Integer.parseInt(remainder2.substring(0, remainder2.indexOf(',')));
                    remainder3 = remainder2.substring(remainder2.indexOf(',') + 1);
                }
                if (remainder2.indexOf(',') == -1) {
                    value = Integer.parseInt(remainder2);
                    remainder3 = "";
                }
                //
                IntNode node = new IntNode(r, c, value);
                sparseMatrix1.insertNode(node);
                //
                elements1 = remainder3;
            }

            if (!elements2.equals("")){
                int r= Integer.parseInt(elements2.substring(0,elements2.indexOf(',')));
                String remainder1= elements2.substring(elements2.indexOf(',')+1);
                //
                int c= Integer.parseInt(remainder1.substring(0,remainder1.indexOf(',')));
                String remainder2= remainder1.substring(remainder1.indexOf(',')+1);
                //
                int value=0;
                String remainder3="";
                //
                if (remainder2.indexOf(',')!=-1){
                    value= Integer.parseInt(remainder2.substring(0,remainder2.indexOf(',')));
                    remainder3= remainder2.substring(remainder2.indexOf(',')+1);
                }
                if (remainder2.indexOf(',')==-1){
                    value= Integer.parseInt(remainder2);
                    remainder3= "";
                }
                //
                IntNode node= new IntNode(r,c,value);
                sparseMatrix2.insertNode(node);
                //
                elements2= remainder3;
            }
        }

    }
}






class LongMatrix{
    private final int numberOfRows;
    private final int numberOfColumns;
    private LongNode firstNode;
    //private IntNode lastNode;
    //size
    public LongMatrix(int numberOfRows , int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public LongNode getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(LongNode firstNode) {
        this.firstNode = firstNode;
    }
    /*public IntNode getLastNode() {
        return lastNode;
    }

    public void setLastNode(IntNode lastNode) {
        this.lastNode = lastNode;
    }*/



    void insertNodeInOrder(LongNode node, int mr, int mc){
        if (firstNode==null){
            firstNode= node;
            //lastNode= node;
        }
        else{
            if (LongNode.compare(node,firstNode)==2){
                LongNode next= this.getFirstNode();
                node.setNext(next);
                this.setFirstNode(node);
            }
            else {
                /*if (node.nodeNumber(mr, mc) > lastNode.nodeNumber(mr, mc)) {
                    Node previous= lastNode;
                    lastNode= node;
                    previous.setNext(lastNode);
                    lastNode.setPrevious(previous);
                }*/
                if (LongNode.compare(node,firstNode)==0){
                    this.getFirstNode().setValue( this.getFirstNode().getValue() + node.getValue() );
                }
                else {
                    LongNode loopNode = this.getFirstNode();
                    while (LongNode.compare(node,loopNode)==1) {
                        if (loopNode.getNext()!=null) {
                            if (LongNode.compare(node,loopNode.getNext())==2) {

                                LongNode next = loopNode.getNext();
                                loopNode.setNext(node);
                                node.setNext(next);
                                break;
                            }
                            if (LongNode.compare(node,loopNode.getNext())==0) {
                                loopNode.getNext().setValue(loopNode.getNext().getValue() + node.getValue());
                                break;
                            }
                            loopNode = loopNode.getNext();
                        }

                        else {
                            loopNode.setNext(node);
                            break;
                        }
                    }
                }
            }
        }
    }


    /*long getValue(int r, int c){
        Node node= firstNode;
        long value= 0;
        while (node!=null){
            if (node.getRow()==r && node.getColumn()==c){
                value= node.getValue();
                break;
            }
            node= node.getNext();
        }
        return value;
    }


    Node getNode(int r, int c){
        Node node= firstNode;
        Node myNode= null;
        while (node!=null){
            if (node.getRow()==r && node.getColumn()==c){
                myNode= node;
                break;
            }
            node= node.getNext();
        }
        return myNode;
    }*/


    @Override
    public String toString() {
        StringBuilder s= new StringBuilder(this.numberOfRows + "," + this.numberOfColumns);
        LongNode node = firstNode;

        long count=0;
        while (node!=null){

            if (node.getValue()!=0) {
                s.append(",");  //***
                count++;
                if (node.getNext() == null) {
                    s.append(node.toString());
                } else {
                    s.append(node.toString());
                }
            }

            node= node.getNext();
        }

        if (firstNode==null || count==0){
            s = new StringBuilder(this.numberOfRows + "," + this.numberOfColumns + ",0");
        }
        return s.toString();
    }
}


























class IntMatrix{
    private final int numberOfRows;
    private final int numberOfColumns;
    private IntNode firstNode;
    //private IntNode lastNode;
    //size
    public IntMatrix(int numberOfRows , int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public IntNode getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(IntNode firstNode) {
        this.firstNode = firstNode;
    }

    /*public IntNode getLastNode() {
        return lastNode;
    }

    public void setLastNode(IntNode lastNode) {
        this.lastNode = lastNode;
    }*/

    void insertNode(IntNode node){    //بررسی عملکرد
        if (firstNode==null){
            this.setFirstNode(node);
            //lastNode= node;
        }
        else{
            IntNode intNode= this.getFirstNode();
            node.setNext(intNode);
            this.setFirstNode(node);
        }
    }






    void insertNodeInOrder(IntNode node, int mr, int mc){
        if (firstNode==null){
            firstNode= node;
            //lastNode= node;
        }
        else{
            if (IntNode.compare(node,firstNode)==2){
                IntNode next= this.getFirstNode();
                node.setNext(next);
                this.setFirstNode(node);
            }
            else {
                /*if (node.nodeNumber(mr, mc) > lastNode.nodeNumber(mr, mc)) {
                    Node previous= lastNode;
                    lastNode= node;
                    previous.setNext(lastNode);
                    lastNode.setPrevious(previous);
                }*/
                if (IntNode.compare(node,firstNode)==0){
                    this.getFirstNode().setValue( this.getFirstNode().getValue() + node.getValue() );
                }
                else {
                    IntNode loopNode = this.getFirstNode();
                    while (IntNode.compare(node,loopNode)==1) {
                        if (loopNode.getNext()!=null) {
                            if (IntNode.compare(node,loopNode.getNext())==2) {

                                IntNode next = loopNode.getNext();
                                loopNode.setNext(node);
                                node.setNext(next);
                                break;
                            }
                            if (IntNode.compare(node,loopNode.getNext())==0) {
                                loopNode.getNext().setValue(loopNode.getNext().getValue() + node.getValue());
                                break;
                            }
                            loopNode = loopNode.getNext();
                        }

                        else {
                            loopNode.setNext(node);
                            break;
                        }
                    }
                }
            }
        }
    }


    /*long getValue(int r, int c){
        Node node= firstNode;
        long value= 0;
        while (node!=null){
            if (node.getRow()==r && node.getColumn()==c){
                value= node.getValue();
                break;
            }
            node= node.getNext();
        }
        return value;
    }


    Node getNode(int r, int c){
        Node node= firstNode;
        Node myNode= null;
        while (node!=null){
            if (node.getRow()==r && node.getColumn()==c){
                myNode= node;
                break;
            }
            node= node.getNext();
        }
        return myNode;
    }*/

    IntMatrix newSum(IntMatrix matrix){
        IntMatrix sum= new IntMatrix(matrix.numberOfRows, matrix.numberOfColumns);

        IntNode node1 = this.getFirstNode();
        IntNode node2 = matrix.getFirstNode();

        while (node1!=null    ||     node2!=null){
            if (node1!=null) {
                int r = node1.getRow();
                int c = node1.getColumn();
                int value = node1.getValue();

                if (value != 0) {
                    IntNode node = new IntNode(r, c, value);
                    sum.insertNodeInOrder(node, sum.numberOfRows, sum.numberOfColumns);
                }

                node1 = node1.getNext();
            }
            if (node2!=null){
                int r= node2.getRow();
                int c= node2.getColumn();
                int value= node2.getValue() ;

                if (value!=0){
                    IntNode node= new IntNode(r,c,value);
                    sum.insertNodeInOrder(node,sum.numberOfRows,sum.numberOfColumns);
                }
                node2= node2.getNext();
            }
        }
        return sum;
    }





























    IntMatrix sum (IntMatrix matrix){
        IntMatrix sum= new IntMatrix(matrix.numberOfRows, matrix.numberOfColumns);

        IntNode node1 = this.getFirstNode();
        while (node1!=null){
            int r= node1.getRow();
            int c= node1.getColumn();
            int value= node1.getValue() ;

            if (value!=0){
                IntNode node= new IntNode(r,c,value);
                sum.insertNodeInOrder(node,sum.numberOfRows,sum.numberOfColumns);
            }

            node1= node1.getNext();
        }

        IntNode node2 = matrix.getFirstNode();
        while (node2!=null){
            int r= node2.getRow();
            int c= node2.getColumn();
            int value= node2.getValue() ;

            if (value!=0){
                IntNode node= new IntNode(r,c,value);
                sum.insertNodeInOrder(node,sum.numberOfRows,sum.numberOfColumns);
            }
            node2= node2.getNext();
        }
        return sum;
    }



    LongMatrix mul (IntMatrix matrix){
        LongMatrix mul= new LongMatrix(this.numberOfRows, matrix.numberOfColumns);

        IntNode node1 = this.getFirstNode();
        while (node1!=null){

            int r1= node1.getRow();
            int c1= node1.getColumn();
            long value1= node1.getValue();

            IntNode node2 = matrix.getFirstNode();

            if (node2==null){break;}   // ezafe kardam

            while (node2!=null){

                int r2= node2.getRow();
                int c2= node2.getColumn();
                long value2= node2.getValue();

                if (c1==r2) {
                    //Node node = mul.getNode(r1, c2);
                    long value= value1*value2;
                    //if (node == null) {
                    LongNode node = new LongNode(r1, c2, value);
                    //}
                    /*else {
                        node.setValue( node.getValue()+value );
                    }*/
                    if (node.getValue()!=0) {   //*********************
                        mul.insertNodeInOrder(node, mul.getNumberOfRows(), mul.getNumberOfColumns());
                    }
                }
                node2= node2.getNext();

            }
            node1= node1.getNext();
        }
        return mul;
    }





    @Override
    public String toString() {
        StringBuilder s= new StringBuilder(this.numberOfRows + "," + this.numberOfColumns);
        IntNode node = firstNode;

        long count=0;
        while (node!=null){

            if (node.getValue()!=0) {
                s.append(",");  //***
                count++;
                if (node.getNext() == null) {
                    s.append(node.toString());
                } else {
                    s.append(node.toString());
                }
            }

            node= node.getNext();
        }

        if (firstNode==null || count==0){
            s = new StringBuilder(this.numberOfRows + "," + this.numberOfColumns + ",0");
        }
        return s.toString();
    }
}































































































































































class LongNode {
    private int row;
    private int column;
    private long value;
    private LongNode next;
    //previous
    public LongNode(int row, int column, long value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public long getValue() {
        return value;
    }
    public void setValue(long value) {
        this.value = value;
    }
    public LongNode getNext() {
        return next;
    }
    public void setNext(LongNode next) {
        this.next = next;
    }
    static int compare(LongNode node1, LongNode node2){
        int compare=0;
        if (node1.getRow()>node2.getRow()){
            compare=1;
        }
        if (node1.getRow()==node2.getRow()  &&  node1.getColumn()>node2.getColumn()){
            compare=1;
        }

        if (node1.getRow()<node2.getRow()){
            compare=2;
        }
        if (node1.getRow()==node2.getRow()  &&  node1.getColumn()<node2.getColumn()){
            compare=2;
        }
        return compare;
    }
    @Override
    public String toString() {
        return row + "," + column + "," + value;
    }
}



class IntNode {
    private int row;
    private int column;
    private int value;
    private IntNode next;
    //previous
    public IntNode(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public IntNode getNext() {
        return next;
    }
    public void setNext(IntNode next) {
        this.next = next;
    }
    static int compare(IntNode node1, IntNode node2){
        int compare=0;
        if (node1.getRow()>node2.getRow()){
            compare=1;
        }
        if (node1.getRow()==node2.getRow()  &&  node1.getColumn()>node2.getColumn()){
            compare=1;
        }

        if (node1.getRow()<node2.getRow()){
            compare=2;
        }
        if (node1.getRow()==node2.getRow()  &&  node1.getColumn()<node2.getColumn()){
            compare=2;
        }
        return compare;
    }
    @Override
    public String toString() {
        return row + "," + column + "," + value;
    }
}






