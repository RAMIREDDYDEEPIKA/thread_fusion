class MatrixPojo implements Runnable
{
    private final int[][] matrixA;
    private final int[][] matrixB;
    private final int[][] result;
    private final int row;
    private final int col;

    public MatrixPojo(int[][] matrixA, int[][] matrixB, int[][] result, int row, int col)
    {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.row = row;
        this.col = col;
    }

    public void run()
    {
        int sum=0;
        for(int k=0;k< matrixA[0].length;k++){
            sum += matrixA[row][k]*matrixB[k][col];
        }
        result[row][col]=sum;
    }
}

public class MatrixMultiplication
{
    public static void main(String[] args)
    {
        int[][] matrixA={
                {2,4,1},
                {9,5,3},
                {8,5,2}};
        int[][] matrixB={
                {5,3,7},
                {9,2,1},
                {8,2,9}};
        int rowsA=matrixA.length;
        //int colsA=matrixA[0].length;
        int colsB=matrixB[0].length;

        int[][] result =new int[rowsA][colsB];

        Thread[][] thread=new Thread[rowsA][colsB];
        for(int i=0;i<rowsA;i++){
            for(int j=0;j<colsB;j++){
                thread[i][j]=new Thread(new MatrixPojo(matrixA,matrixB,result,i,j));
                thread[i][j].start();
            }
        }

        for(int i=0;i<rowsA;i++){
            for(int j=0;j<colsB;j++){
                try {
                    thread[i][j].join();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Resultant matrix: ");
        for(int i=0;i<rowsA;i++){
            for(int j=0;j<colsB;j++){
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
    }
}