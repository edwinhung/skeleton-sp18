public class Planet {
    private static final double G = 6.67e-11;
    private static final String imgDir = "images/";
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return G * mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double r = this.calcDistance(p);
        return this.calcForceExertedBy(p) * dx / r;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double r = this.calcDistance(p);
        return this.calcForceExertedBy(p) * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] pArr) {
        double res = 0;
        for (Planet p: pArr) {
            if (this.equals(p)) {
                continue;
            }
            res += this.calcForceExertedByX(p);
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] pArr) {
        double res = 0;
        for (Planet p: pArr) {
            if (this.equals(p)) {
                continue;
            }
            res += this.calcForceExertedByY(p);
        }
        return res;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;

    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, imgDir + imgFileName);
    }

}
