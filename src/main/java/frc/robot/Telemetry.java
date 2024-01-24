// Source code is decompiled from a .class file using FernFlower decompiler.
package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.DoubleArrayPublisher;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.PubSubOption;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class Telemetry {
   private final double MaxSpeed;
   private final NetworkTableInstance inst = NetworkTableInstance.getDefault();
   private final NetworkTable table;
   private final DoubleArrayPublisher fieldPub;
   private final StringPublisher fieldTypePub;
   private final NetworkTable driveStats;
   private final DoublePublisher velocityX;
   private final DoublePublisher velocityY;
   private final DoublePublisher speed;
   private final DoublePublisher odomPeriod;
   private Pose2d m_lastPose;
   private double lastTime;
   private final Mechanism2d[] m_moduleMechanisms;
   private final MechanismLigament2d[] m_moduleSpeeds;
   private final MechanismLigament2d[] m_moduleDirections;

   public Telemetry(double maxSpeed) {
      this.table = this.inst.getTable("Pose");
      this.fieldPub = this.table.getDoubleArrayTopic("robotPose").publish(new PubSubOption[0]);
      this.fieldTypePub = this.table.getStringTopic(".type").publish(new PubSubOption[0]);
      this.driveStats = this.inst.getTable("Drive");
      this.velocityX = this.driveStats.getDoubleTopic("Velocity X").publish(new PubSubOption[0]);
      this.velocityY = this.driveStats.getDoubleTopic("Velocity Y").publish(new PubSubOption[0]);
      this.speed = this.driveStats.getDoubleTopic("Speed").publish(new PubSubOption[0]);
      this.odomPeriod = this.driveStats.getDoubleTopic("Odometry Period").publish(new PubSubOption[0]);
      this.m_lastPose = new Pose2d();
      this.lastTime = Utils.getCurrentTimeSeconds();
      this.m_moduleMechanisms = new Mechanism2d[]{new Mechanism2d(1.0, 1.0), new Mechanism2d(1.0, 1.0), new Mechanism2d(1.0, 1.0), new Mechanism2d(1.0, 1.0)};
      this.m_moduleSpeeds = new MechanismLigament2d[]{(MechanismLigament2d)this.m_moduleMechanisms[0].getRoot("RootSpeed", 0.5, 0.5).append(new MechanismLigament2d("Speed", 0.5, 0.0)), (MechanismLigament2d)this.m_moduleMechanisms[1].getRoot("RootSpeed", 0.5, 0.5).append(new MechanismLigament2d("Speed", 0.5, 0.0)), (MechanismLigament2d)this.m_moduleMechanisms[2].getRoot("RootSpeed", 0.5, 0.5).append(new MechanismLigament2d("Speed", 0.5, 0.0)), (MechanismLigament2d)this.m_moduleMechanisms[3].getRoot("RootSpeed", 0.5, 0.5).append(new MechanismLigament2d("Speed", 0.5, 0.0))};
      this.m_moduleDirections = new MechanismLigament2d[]{(MechanismLigament2d)this.m_moduleMechanisms[0].getRoot("RootDirection", 0.5, 0.5).append(new MechanismLigament2d("Direction", 0.1, 0.0, 0.0, new Color8Bit(Color.kWhite))), (MechanismLigament2d)this.m_moduleMechanisms[1].getRoot("RootDirection", 0.5, 0.5).append(new MechanismLigament2d("Direction", 0.1, 0.0, 0.0, new Color8Bit(Color.kWhite))), (MechanismLigament2d)this.m_moduleMechanisms[2].getRoot("RootDirection", 0.5, 0.5).append(new MechanismLigament2d("Direction", 0.1, 0.0, 0.0, new Color8Bit(Color.kWhite))), (MechanismLigament2d)this.m_moduleMechanisms[3].getRoot("RootDirection", 0.5, 0.5).append(new MechanismLigament2d("Direction", 0.1, 0.0, 0.0, new Color8Bit(Color.kWhite)))};
      this.MaxSpeed = maxSpeed;
   }

   public void telemeterize(SwerveDrivetrain.SwerveDriveState state) {
      Pose2d pose = state.Pose;
      this.fieldTypePub.set("Field2d");
      this.fieldPub.set(new double[]{pose.getX(), pose.getY(), pose.getRotation().getDegrees()});
      double currentTime = Utils.getCurrentTimeSeconds();
      double diffTime = currentTime - this.lastTime;
      this.lastTime = currentTime;
      Translation2d distanceDiff = pose.minus(this.m_lastPose).getTranslation();
      this.m_lastPose = pose;
      Translation2d velocities = distanceDiff.div(diffTime);
      this.speed.set(velocities.getNorm());
      this.velocityX.set(velocities.getX());
      this.velocityY.set(velocities.getY());
      this.odomPeriod.set(state.OdometryPeriod);

      for(int i = 0; i < 4; ++i) {
         this.m_moduleSpeeds[i].setAngle(state.ModuleStates[i].angle);
         this.m_moduleDirections[i].setAngle(state.ModuleStates[i].angle);
         this.m_moduleSpeeds[i].setLength(state.ModuleStates[i].speedMetersPerSecond / (2.0 * this.MaxSpeed));
         SmartDashboard.putData("Module " + i, this.m_moduleMechanisms[i]);
      }

   }
}
