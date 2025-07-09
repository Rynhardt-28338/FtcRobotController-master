package org.firstinspires.ftc.teamcode;

public class RobotState {


    double curentX;
    double curentY;
    double curentDir;
    int armPos;
    int slidePos;
    double wristPos;
    String gripperPos;

    public void setCurentX(double curentX){this.curentX = curentX;}
    public void setCurentY(double curentY){this.curentY = curentY;}
    public void setCurentDir(double curentDir){this.curentDir = curentDir;}

    public double getCurentX(){return curentX;}
    public double getCurentY(){return curentY;}
    public double getCurentDir(){return curentDir;}

    public void setArmPos(int armPos){this.armPos = armPos;}
    public void setSlidePos(int slidePos){this.slidePos = slidePos;}

    public int getArmPos(){return armPos;}
    public int getSlidePos(){return slidePos;}

    public void setWristPos(double wristPos){this.wristPos = wristPos;}
    public void setGripperPos(String gripperPos){this.gripperPos = gripperPos;}

    public double getWristPos(){return wristPos;}
    public String getGripperPos(){return gripperPos;}

}
