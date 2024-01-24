//Source code is decompiled from a .class file using FernFlower decompiler.
package frc.robot.generated;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstantsFactory;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.ClosedLoopOutputType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants.SteerFeedbackType;
import edu.wpi.first.math.util.Units;
import frc.robot.CommandSwerveDrivetrain;

public class TunerConstants {
   private static final Slot0Configs steerGains = (new Slot0Configs()).withKP(100.0).withKI(0.0).withKD(0.2).withKS(0.0).withKV(1.5).withKA(0.0);
   private static final Slot0Configs driveGains = (new Slot0Configs()).withKP(3.0).withKI(0.0).withKD(0.0).withKS(0.0).withKV(0.0).withKA(0.0);
   private static final SwerveModule.ClosedLoopOutputType steerClosedLoopOutput;
   private static final SwerveModule.ClosedLoopOutputType driveClosedLoopOutput;
   private static final double kSlipCurrentA = 300.0;
   public static final double kSpeedAt12VoltsMps = 4.73;
   private static final double kCoupleRatio = 3.5714285714285716;
   private static final double kDriveGearRatio = 6.746031746031747;
   private static final double kSteerGearRatio = 21.428571428571427;
   private static final double kWheelRadiusInches = 2.0;
   private static final boolean kSteerMotorReversed = true;
   private static final boolean kInvertLeftSide = false;
   private static final boolean kInvertRightSide = true;
   private static final String kCANbusName = "";
   private static final int kPigeonId = 9;
   private static final double kSteerInertia = 1.0E-5;
   private static final double kDriveInertia = 0.001;
   private static final double kSteerFrictionVoltage = 0.25;
   private static final double kDriveFrictionVoltage = 0.25;
   private static final SwerveDrivetrainConstants DrivetrainConstants;
   private static final SwerveModuleConstantsFactory ConstantCreator;
   private static final int kFrontLeftDriveMotorId = 10;
   private static final int kFrontLeftSteerMotorId = 11;
   private static final int kFrontLeftEncoderId = 21;
   private static final double kFrontLeftEncoderOffset = 0.1171875;
   private static final double kFrontLeftXPosInches = 11.5;
   private static final double kFrontLeftYPosInches = 11.5;
   private static final int kFrontRightDriveMotorId = 19;
   private static final int kFrontRightSteerMotorId = 20;
   private static final int kFrontRightEncoderId = 12;
   private static final double kFrontRightEncoderOffset = -0.117431640625;
   private static final double kFrontRightXPosInches = 11.5;
   private static final double kFrontRightYPosInches = -11.5;
   private static final int kBackLeftDriveMotorId = 13;
   private static final int kBackLeftSteerMotorId = 14;
   private static final int kBackLeftEncoderId = 15;
   private static final double kBackLeftEncoderOffset = -0.460205078125;
   private static final double kBackLeftXPosInches = -11.5;
   private static final double kBackLeftYPosInches = 11.5;
   private static final int kBackRightDriveMotorId = 16;
   private static final int kBackRightSteerMotorId = 17;
   private static final int kBackRightEncoderId = 18;
   private static final double kBackRightEncoderOffset = -0.409423828125;
   private static final double kBackRightXPosInches = -11.5;
   private static final double kBackRightYPosInches = -11.5;
   private static final SwerveModuleConstants FrontLeft;
   private static final SwerveModuleConstants FrontRight;
   private static final SwerveModuleConstants BackLeft;
   private static final SwerveModuleConstants BackRight;
   public static final CommandSwerveDrivetrain DriveTrain;

   static {
      steerClosedLoopOutput = ClosedLoopOutputType.Voltage;
      driveClosedLoopOutput = ClosedLoopOutputType.Voltage;
      DrivetrainConstants = (new SwerveDrivetrainConstants()).withPigeon2Id(9).withCANbusName("");
      ConstantCreator = (new SwerveModuleConstantsFactory()).withDriveMotorGearRatio(6.746031746031747).withSteerMotorGearRatio(21.428571428571427).withWheelRadius(2.0).withSlipCurrent(300.0).withSteerMotorGains(steerGains).withDriveMotorGains(driveGains).withSteerMotorClosedLoopOutput(steerClosedLoopOutput).withDriveMotorClosedLoopOutput(driveClosedLoopOutput).withSpeedAt12VoltsMps(4.73).withSteerInertia(1.0E-5).withDriveInertia(0.001).withSteerFrictionVoltage(0.25).withDriveFrictionVoltage(0.25).withFeedbackSource(SteerFeedbackType.FusedCANcoder).withCouplingGearRatio(3.5714285714285716).withSteerMotorInverted(true);
      FrontLeft = ConstantCreator.createModuleConstants(11, 10, 21, 0.1171875, Units.inchesToMeters(11.5), Units.inchesToMeters(11.5), false);
      FrontRight = ConstantCreator.createModuleConstants(20, 19, 12, -0.117431640625, Units.inchesToMeters(11.5), Units.inchesToMeters(-11.5), true);
      BackLeft = ConstantCreator.createModuleConstants(14, 13, 15, -0.460205078125, Units.inchesToMeters(-11.5), Units.inchesToMeters(11.5), false);
      BackRight = ConstantCreator.createModuleConstants(17, 16, 18, -0.409423828125, Units.inchesToMeters(-11.5), Units.inchesToMeters(-11.5), true);
      DriveTrain = new CommandSwerveDrivetrain(DrivetrainConstants, new SwerveModuleConstants[]{FrontLeft, FrontRight, BackLeft, BackRight});
   }

 public TunerConstants() {
   }
} 
