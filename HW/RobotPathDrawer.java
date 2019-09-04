import java.awt.*;
public class RobotPathDrawer extends javax.swing.JPanel {
    int n = 10;
    Point[] points;
    TwoPoint[] boundaryPair;
    Point a;
    Point b;
    TwoPoint[] ansPath;
    public RobotPathDrawer(Point[] points, TwoPoint[] TwoPoints,Point a,Point b,TwoPoint[] ans) {
        this.points = points;
        this.boundaryPair = TwoPoints;
        this.a=a;
        this.b=b;
        ansPath = ans;
        initComponents();
    }
    public void paintComponent(Graphics page) {
        int radius = 10;
        for (int i = 0; i < points.length; i++) {
            page.setColor(Color.BLACK);
            page.drawOval(points[i].x - radius / 2, points[i].y - radius / 2, 10, 10);
            page.setColor(Color.red);
            page.fillOval(points[i].x - radius / 2, points[i].y - radius / 2, 10, 10);
        }
        page.setColor(Color.red);
        for (int i = 0; i < boundaryPair.length&&boundaryPair[i]!=null; i++) {
            page.drawLine(boundaryPair[i].a.x,boundaryPair[i].a.y,boundaryPair[i].b.x,boundaryPair[i].b.y);
        }
        page.setColor(Color.BLACK);
        page.drawOval(a.x - radius / 2, a.y - radius / 2, 10, 10);
        page.drawOval(b.x - radius / 2, b.y - radius / 2, 10, 10);
        page.setColor(Color.yellow);
        page.fillOval(a.x - radius / 2, a.y - radius / 2, 10, 10);
        page.fillOval(b.x - radius / 2, b.y - radius / 2, 10, 10);
        page.setColor(Color.magenta);
        for (int i = 0; i < ansPath.length&&ansPath[i]!=null; i++) {
            page.drawLine(ansPath[i].a.x,ansPath[i].a.y,ansPath[i].b.x,ansPath[i].b.y);
        }
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }
}
