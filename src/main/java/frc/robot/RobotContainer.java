//  Source code is decompiled from a .class file using FernFlower decompiler.
package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.generated.TunerConstants;

public class RobotContainer {
   private double MaxSpeed = 6.0;
   private double MaxAngularRate = 4.71238898038469;
   private final CommandXboxController joystick = new CommandXboxController(0);
   private final CommandSwerveDrivetrain drivetrain;
   private final SwerveRequest.FieldCentric drive;
   private final SwerveRequest.SwerveDriveBrake brake;
   private final SwerveRequest.PointWheelsAt point;
   private final Telemetry logger;

   private void configureBindings() {
      this.drivetrain.setDefaultCommand(this.drivetrain.applyRequest(() -> {
         return this.drive.withVelocityX(-this.joystick.getLeftY() * this.MaxSpeed).withVelocityY(-this.joystick.getLeftX() * this.MaxSpeed).withRotationalRate(-this.joystick.getRightX() * this.MaxAngularRate);
      }));
      this.joystick.a().whileTrue(this.drivetrain.applyRequest(() -> {
         return this.brake;
      }));
      this.joystick.b().whileTrue(this.drivetrain.applyRequest(() -> {
         return this.point.withModuleDirection(new Rotation2d(-this.joystick.getLeftY(), -this.joystick.getLeftX()));
      }));
      this.joystick.leftBumper().onTrue(this.drivetrain.runOnce(() -> {
         this.drivetrain.seedFieldRelative();
      }));
      if (Utils.isSimulation()) {
         this.drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90.0)));
      }

      Telemetry var10001 = this.logger;
      this.drivetrain.registerTelemetry(var10001::telemeterize);
   }

   public RobotContainer() {
      this.drivetrain = TunerConstants.DriveTrain;
      this.drive = (new SwerveRequest.FieldCentric()).withDeadband(this.MaxSpeed * 0.1).withRotationalDeadband(this.MaxAngularRate * 0.1).withDriveRequestType(DriveRequestType.OpenLoopVoltage);
      this.brake = new SwerveRequest.SwerveDriveBrake();
      this.point = new SwerveRequest.PointWheelsAt();
      this.logger = new Telemetry(this.MaxSpeed);
      this.configureBindings();
   }

   public Command getAutonomousCommand() {
      return Commands.print("No autonomous command configured");
   }
}
