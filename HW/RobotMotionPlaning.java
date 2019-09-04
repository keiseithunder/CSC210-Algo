import javax.swing.*;
public class RobotMotionPlaning {
    static int n = 9;
    public static void main(String[] args) {
        Point point[] = new Point[n];
        point[0] = new Point(50,100);
        point[1] = new Point(200,300);
        point[2] = new Point(250,80);
        point[3] = new Point(400,300);
        point[4] = new Point(600,150);
        point[5] = new Point(120,360);
        point[6] = new Point(90,425);
        point[7] = new Point(500,140);
        point[8] = new Point(270,210);
//        point[9] = new Point(420,90);
        TwoPoint[] convexHull = findBoundary(point);
        JFrame frame = new JFrame("convex hull");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Convex panel = new Convex(point,convexHull,TwoPoint.numberOfTwoPoint);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        Point a = new Point(10,100);
        Point b = new Point(800,200);
        TwoPoint[] ansOfRobotMotion = findRobotMotion(point,a,b);
        JFrame frame1 = new JFrame("Robot Motion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RobotPathDrawer panel1 = new RobotPathDrawer(point,convexHull,a,b,ansOfRobotMotion);
        frame1.getContentPane().add(panel1);
        frame1.pack();
        frame1.setVisible(true);
    }
    public static TwoPoint[] findBoundary(Point[] points){
        int a,b,c,cOfK,boundaryCount;
        boundaryCount=0;
        TwoPoint[] boundary = new TwoPoint[points.length];
        for (int i = 0; i <n-1; i++) {
            for (int j = i+1; j < n; j++) {
                a = points[j].y - points[i].y;
                b = points[i].x - points[j].x;
                c = points[i].x*points[j].y - points[j].x*points[i].y;
                int lessThan=0;
                int moreThan=0;
                for (int k = 0; k < points.length; k++) {
                    cOfK = a*points[k].x + b*points[k].y;

                    if (cOfK>=c){
                        moreThan++;
                    }
                    if(cOfK<=c){
                        lessThan++;
                    }
                    if(lessThan!=k+1&&moreThan!= k+1){
                        break;
                    }
                }
                if(lessThan==points.length || moreThan==points.length){
                    boundary[boundaryCount]  = new TwoPoint(points[i],points[j]);
                    TwoPoint.numberOfTwoPoint = ++boundaryCount;
                }
            }
        }
        return boundary;
    }
    //a and b must not be inside of the convexnull
    public static TwoPoint[] findRobotMotion(Point[] points,Point a,Point b){
        Point[] newPoints = new Point[points.length+2];
        newPoints[0] = a;
        newPoints[1] = b;
        for (int i = 0; i < points.length; i++) {
            newPoints[2+i] = points[i];
        }
        TwoPoint[] newConvex = findBoundary(newPoints);
        //findPath
        TwoPoint[] PathFromA = new TwoPoint[2];
        int indexOfPath = 0;
        for (int i = 0; i < newConvex.length&&newConvex[i]!=null; i++) {
            if(newConvex[i].isInTwoPoint(a)){
                PathFromA[indexOfPath++] = newConvex[i];
            }
        }
        System.out.println();
        TwoPoint[] ansPath1 = new TwoPoint[newConvex.length];
        ansPath1[0] = PathFromA[0];
        findPath(a,b,newConvex,PathFromA[0].whichPair(a),ansPath1,1);
        TwoPoint[] ansPath2 = new TwoPoint[newConvex.length];
        ansPath2[0] = PathFromA[1];
        findPath(a,b,newConvex,PathFromA[1].whichPair(a),ansPath2,1);
        double distance1 = 0;
        double distance2 = 0;
        for (int i = 0; i < ansPath1.length&&ansPath1[i]!=null; i++) {
            distance1+=ansPath1[i].findDistance();
        }
        for (int i = 0; i < ansPath2.length&&ansPath2[i]!=null; i++) {
            distance2+=ansPath2[i].findDistance();
        }
        System.out.println("Available Path 1");
        for (TwoPoint p:ansPath1
             ) {
            if(p!=null)
                System.out.print(" Line : "+p+" ");
        }
        System.out.println("\nAvailable Path 1 : Distance "+ distance1);
        System.out.println("Available Path 2");
        for (TwoPoint p:ansPath2
        ) {
            if(p!=null)
                System.out.print(" Line : "+p+" ");
        }
        System.out.println("\nAvailable Path 2 : Distance "+ distance2);

        if(distance1<distance2){
            return ansPath1;
        }else{
            return ansPath2;
        }

    }
    public static TwoPoint[] findPath(Point a , Point b , TwoPoint[] boundary, Point nextOfA,TwoPoint[] ans,int index){
        if(index==1){
            if (ans[0].whichPair(a)==b){
                return ans;
            }
        }
        for (int i = 0; i < boundary.length&&boundary[i]!=null; i++) {
            if(boundary[i].isInTwoPoint(nextOfA)&&!boundary[i].isInTwoPoint(a)){
                if (boundary[i].isInTwoPoint(b)) {
                    ans[index++] = boundary[i];
                    return ans;
                }
                a = nextOfA;
                ans[index++]= boundary[i];
                nextOfA=boundary[i].whichPair(nextOfA);
            }
        }
        return findPath(a,b,boundary,nextOfA,ans,index);
    }
}
class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "("+x+","+y+")";
    }
}
class TwoPoint{
    Point a ,b;
    public static int numberOfTwoPoint;
    public TwoPoint(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
    public boolean isInTwoPoint(Point p){
        return (p.x == this.a.x && p.y == this.a.y)||(p.x == this.b.x && p.y == this.b.y);
    }
    public Point whichPair(Point point){
        if(point.x == a.x && point.y == a.y){
            return this.b;
        }else if(point.x == b.x && point.y == b.y){
            return this.a;
        }
        return null;
    }
    public double findDistance(){
        return Math.sqrt(Math.pow((a.x-b.x),2)+Math.pow((a.y-b.y),2));
    }
    public String toString(){
        return "("+a.x+","+a.y+"),"+"("+b.x+","+b.y+")";
    }
}
