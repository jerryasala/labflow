/*
 * LabFlow, a Laboratory Information Management System (LIMS), is a software-based solution
 * that makes it easy for labs to effectively manage day-to-day operations. By using LabFLow,
 * a lab can automate workflows, integrate instruments, and manage samples and associated
 * information.
 */

/**
 * This class is the main entry point to LabFlow, a lab info management system
 */
public class LabFlow {
  public static void main(String[] args) {
    IModel model = new ModelImpl();
    IController controller = new ControllerImpl(model);
    controller.go();
  }
}
