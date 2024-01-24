// Source code is decompiled from a .class file using FernFlower decompiler.
package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import java.util.function.Supplier;

public class CommandSwerveDrivetrain extends SwerveDrivetrain implements Subsystem {
   private static final double kSimLoopPeriod = 0.005;
   private Notifier m_simNotifier = null;
   private double m_lastSimTime;

   public CommandSwerveDrivetrain(SwerveDrivetrainConstants driveTrainConstants, double OdometryUpdateFrequency, SwerveModuleConstants... modules) {
      super(driveTrainConstants, OdometryUpdateFrequency, modules);
      if (Utils.isSimulation()) {
         this.startSimThread();
      }

   }

   public CommandSwerveDrivetrain(SwerveDrivetrainConstants driveTrainConstants, SwerveModuleConstants... modules) {
      super(driveTrainConstants, modules);
      if (Utils.isSimulation()) {
         this.startSimThread();
      }

   }

   public Command applyRequest(Supplier<SwerveRequest> requestSupplier) {
      return this.run(() -> {
         this.setControl((SwerveRequest)requestSupplier.get());
      });
   }

   private void startSimThread() {
      this.m_lastSimTime = Utils.getCurrentTimeSeconds();
      this.m_simNotifier = new Notifier(() -> {
         double currentTime = Utils.getCurrentTimeSeconds();
         double deltaTime = currentTime - this.m_lastSimTime;
         this.m_lastSimTime = currentTime;
         this.updateSimState(deltaTime, RobotController.getBatteryVoltage());
      });
      this.m_simNotifier.startPeriodic(0.005);
   }
}
