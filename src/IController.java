import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * This interface represents the controller of LIMS
 */
public interface IController extends ActionListener, ItemListener {

  /**
   * Calls on view to create the GUI
   */
  void go();

  /**
   * Gets sample info from the view and passes to the model to add the sample, then asks the view to
   * add the sample to the GUI
   */
  void controllerAddSample();

  /**
   * Gets an array of names of samples from the model and sends it to view, then asks the view to
   * delete selected samples and return an array of indices of the deleted samples in the original
   * list of tests, then finally asks the model to delete samples from given indices (adjusted for
   * previous deletions)
   */
  void controllerDeleteSample();

  /**
   * Asks the view to return a list of the selected tests and a list of the selected samples, then
   * asks the model to add tests to each selected sample
   */
  void controllerAddTestsToSample();

  /**
   * Gets a list of selected samples from the view, and for each sample, asks the model to return
   * the list of tests for the sample, then asks the view to display the test(s).
   */
  void controllerShowTests();

  /**
   * Gets test info from the view and passes to the model to add the test, then asks the view to add
   * the test to the GUI
   */
  void controllerAddTest();

  /**
   * Gets an array of names of tests from the model and sends it to view, then asks the view to
   * delete selected tests and return an array of indices of the deleted tests in the original list
   * of tests, then finally asks the model to delete tests from given indices (adjusted for previous
   * deletions)
   */
  void controllerDeleteTest();

  /**
   * Gets a list of selected tests from the view, and for each test, asks the model to return the
   * list of samples for the test, then asks the view to display the sample(s).
   */
  void controllerShowSamples();

  /**
   * Gets equipment info from the view and passes to the model to add the equipment, then asks the
   * view to add the equipment to the GUI
   */
  void controllerAddEquipment();

  /**
   * Gets an array of names of equipment from the model and sends it to view, then asks the view to
   * delete selected equipment and return an array of indices of the deleted equipment in the
   * original list of equipment, then finally asks the model to delete equipment from given indices
   * (adjusted for previous deletions)
   */
  void controllerDeleteEquipment();

  /**
   * Gets the list of selected equipment from view, then for each equipment, gets info from the
   * model and asks the view to show info
   */
  void controllerShowEquipmentInfo();

  /**
   * Asks the model to sort samples by ID, then gets a list of names of samples from model and sends
   * to the view to reorder the samples
   */
  void controllerSortSamplesByID();

  /**
   * Asks the model to sort tests by duration, then gets a list of names of tests from model and
   * sends to the view to reorder the tests
   */
  void controllerSortTestsByDuration();

  /**
   * Asks the model to sort equipment by next service due, then gets a list of names of equipment
   * from model and sends to the view to reorder the equipment
   */
  void controllerSortEquipmentByServiceUrgency();

  /**
   * Asks the model to clear the list of samples, then asks the view to clear the sample display
   * area
   */
  void controllerClearSamples();

  /**
   * Asks the model to clear the list of tests, then asks the view to clear the test display area
   */
  void controllerClearTests();

  /**
   * Asks the model to clear the list of equipment, then asks the view to clear the equipment
   * display area
   */
  void controllerClearEquipment();

  /**
   * Helper method to auto generate samples, tests and equipment. Tells view to autogenerate items
   * (which ideally, it should not). This is for the sake of experimentation.
   */
  void controllerAutogenerate();
}
