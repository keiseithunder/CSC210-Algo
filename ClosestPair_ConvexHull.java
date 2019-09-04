/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closestpair_convexhull;

import javax.swing.JFrame;

/**
 *
 * @author CSC318
 */
public class ClosestPair_ConvexHull {
    static int n = 10;
    static int bC;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int point[][] = new int[n][2];
        point[0][0] = 50; point[0][1] = 100; // (50,100)
        point[1][0] = 200; point[1][1] = 300; // (200,300)
        point[2][0] = 250; point[2][1] = 80; // (50,100)
        point[3][0] = 400; point[3][1] = 300; // (50,100)
        point[4][0] = 600; point[4][1] = 150; // (50,100)
        point[5][0] = 120; point[5][1] = 360; // (50,100)
        point[6][0] = 90; point[6][1] = 425; // (50,100)
        point[7][0] = 500; point[7][1] = 140; // (50,100)
        point[8][0] = 270; point[8][1] = 210; // (50,100)
        point[9][0] = 420; point[9][1] = 90; // (50,100)
        int pair[] = new int[2];
        pair = closestPair(point);
        System.out.println("point( " + point[pair[0]][0]+","+point[pair[0]][1]+ ")"+"and point( " + point[pair[1]][0]+","+point[pair[1]][1]+ ")");
            
        int[][] boundaryOfPairOfPoints = convexHull(point);
        JFrame frame = new JFrame("Closest Pair Problem");
//        JFrame frame1 = new JFrame("convex hull");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawClosestPair panel = new DrawClosestPair(point, pair,boundaryOfPairOfPoints,bC);
//        DrawConvex panel1 = new DrawConvex(point, pair,boundaryOfPairOfPoints,bC);
//        frame1.getContentPane().add(panel1);
        frame.getContentPane().add(panel);
//        frame1.pack();
        frame.pack();
//        frame1.setVisible(true);
        frame.setVisible(true);
        
        JFrame f1 = new JFrame("Convex");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        convex p1 = new convex(point, pair, boundaryOfPairOfPoints, bC);
        f1.getContentPane().add(p1);
        f1.pack();
        f1.setVisible(true);


    }
    
    public static int[][] convexHull(int[][] points){
// linear ax + by = c        
//a = y2 - y1  b= x1 - x2  c =x1y2 - x2y1
        int a , b ,c , cOfPointK;
        int boundaryCount = 0;
        int[][] boundaryPair = new int[n][2];
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                a = points[j][1] - points[i][1];
                b = points[i][0] - points[j][0];
                c = points[i][0]*points[j][1] - points[j][0]*points[i][1];
                int lessThanC = 0;
                int moreThanC = 0;
                for (int k = 0; k < n; k++) {
                    cOfPointK = a*points[k][0] + b*points[k][1];
                    if(cOfPointK<= c){
                        lessThanC++;
                    }
                    if(cOfPointK>=c){
                        moreThanC++;
                    }
                    if(lessThanC!=k+1&&moreThanC!=k+1){
                        break;//there a point in both side
                    }
                }
                if(lessThanC == n|| moreThanC == n){
                    boundaryPair[boundaryCount][0] = i;
                    boundaryPair[boundaryCount][1] = j;
                    boundaryCount++;
                }
                
            }
        }
        bC=boundaryCount;
        return boundaryPair;
    }
    
    public static int[] closestPair(int[][] points){
        //c^2= a^2+b^2
        //c = sqrt((x1-x2)^2 +(y1-y2)^2)
        //c is the distance between 2 point
        int closestPoint1 = 0; 
        int closestPoint2 = 0;
        double distanceSq=0;
        double shortestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                distanceSq = Math.sqrt(Math.pow(points[i][0] - points[j][0],2) + Math.pow(points[i][1] - points[j][1],2)); 
                if(distanceSq < shortestDistance){
                    shortestDistance = distanceSq; 
                    closestPoint1 = i;
                    closestPoint2 = j;
                }
            }
        }
        int[] result = {closestPoint1,closestPoint2};
        return result;
    }
    
}
