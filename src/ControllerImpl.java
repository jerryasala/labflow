import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Objects;

/**
 * This class implements the IController interface with all its mandated methods
 */
public class ControllerImpl implements IController {
  private final IModel model;
  private final ViewImpl view;

  /**
   * Constructs a controller and instantiates the model
   * @param model the model of the Lab Info Management System
   */
  public ControllerImpl(IModel model) {
    this.model = model;
    this.view = new ViewImpl(this);
  }

  /**
   * Calls on view to create the GUI
   */
  @Override
  public void go() {
    view.viewCreateGUI();
  }

  /**
   * Gets sample info from the view and passes to the model to add the sample,
   * then asks the view to add the sample to the GUI
   */
  @Override
  public void controllerAddSample() {
    if (Objects.equals(view.getSampleInfo()[0].toUpperCase(), "") ||
        Objects.equals(view.getSampleInfo()[1], "")) {
      return;
    }
    model.addSample(new SampleImpl(view.getSampleInfo()[0].toUpperCase(),
        Integer.valueOf(view.getSampleInfo()[1])));
    view.viewAddSample();
  }

  /**
   * Gets an array of names of samples from the model and sends it to view, then
   * asks the view to delete selected samples and return an array of indices of the
   * deleted samples in the original list of tests, then finally asks the model to
   * delete samples from given indices (adjusted for previous deletions)
   */
  @Override
  public void controllerDeleteSample() {

    int count = 0;    // to account for shifting elements after each deletion
    List<Integer> toDelete = view.viewDeleteSample(model.getListOfNamesOfSamples());
    for (Integer index : toDelete) {
      model.deleteSample(index-count);
      count++;
    }
  }

  /**
   * Asks the view to return a list of the selected tests and a list of the selected
   * samples, then asks the model to add tests to each selected sample
   */
  @Override
  public void controllerAddTestsToSample() {
    List<String> tests = view.getTestToAddToSample();
    List<String> samples = view.getSelectedSamples();

    for (String test : tests) {
      for (String sample : samples) {
        model.addTestToSample(sample, test);
      }
    }
  }

  /**
   * Gets a list of selected samples from the view, and for each sample, asks the
   * model to return the list of tests for the sample, then asks the view to
   * display the test(s).
   */
  @Override
  public void controllerShowTests() {
    List<String> samples = view.getSelectedSamples();
    for (String sample : samples) {
      view.viewShowTestsOfSample(model.getTestsForSample(sample));
    }
  }

  /**
   * Gets test info from the view and passes to the model to add the test,
   * then asks the view to add the test to the GUI
   */
  @Override
  public void controllerAddTest() {
    if (Objects.equals(view.getTestInfo()[0].toUpperCase(), "") ||
        Objects.equals(view.getTestInfo()[1], "")) {
      return;
    }
    model.addTest(new LabTestImpl(view.getTestInfo()[0].toUpperCase(),
        Integer.valueOf(view.getTestInfo()[1])));
    view.viewAddTest();
  }

  /**
   * Gets an array of names of tests from the model and sends it to view, then
   * asks the view to delete selected tests and return an array of indices of the
   * deleted tests in the original list of tests, then finally asks the model to
   * delete tests from given indices (adjusted for previous deletions)
   */
  @Override
  public void controllerDeleteTest() {
    int count = 0;    // to account for shifting elements after each deletion
    List<Integer> toDelete = view.viewDeleteTest(model.getListOfNamesOfTests());
    for (Integer index : toDelete) {
      model.deleteTest(index-count);
      count++;
    }
  }

  /**
   * Gets a list of selected tests from the view, and for each test, asks the
   * model to return the list of samples for the test, then asks the view to
   * display the sample(s).
   */
  @Override
  public void controllerShowSamples(){
    List<String> tests = view.getSelectedTests();
    for (String test : tests) {
      view.viewShowSamplesOfTest(model.getSamplesForTest(test));
    }
  }

  /**
   * Gets equipment info from the view and passes to the model to add the equipment,
   * then asks the view to add the equipment to the GUI
   */
  @Override
  public void controllerAddEquipment() {
    if (Objects.equals(view.getEquipmentInfo()[0].toUpperCase(), "") ||
        Objects.equals(view.getEquipmentInfo()[1], "")) {
      return;
    }
    model.addEquipment(new EquipmentImpl(view.getEquipmentInfo()[0].toUpperCase(),
        Integer.valueOf(view.getEquipmentInfo()[1])));
    view.viewAddEquipment();
  }

  /**
   * Gets an array of names of equipment from the model and sends it to view, then
   * asks the view to delete selected equipment and return an array of indices of the
   * deleted equipment in the original list of equipment, then finally asks the model to
   * delete equipment from given indices (adjusted for previous deletions)
   */
  @Override
  public void controllerDeleteEquipment() {
    int count = 0;    // to account for shifting elements after each deletion
    List<Integer> toDelete = view.viewDeleteEquipment(model.getListOfNamesOfEquipment());
    for (Integer index : toDelete) {
      model.deleteEquipment(index-count);
      count++;
    }
  }

  /**
   * Gets the list of selected equipment from view, then for each equipment, gets info
   * from the model and asks the view to show info
   */
  @Override
  public void controllerShowEquipmentInfo() {
    view.cleanUpEquipmentDisplay();
    for (String equipmentName : view.getSelectedEquipment()) {
      int serviceDue = model.getEquipmentInfo(equipmentName);
      view.showEquipmentInfo(equipmentName, serviceDue);
    }
  }

  /**
   * Asks the model to sort samples by ID, then gets a list of names of samples from model
   * and sends to the view to reorder the samples
   */
  @Override
  public void controllerSortSamplesByID() {
    model.sortSamplesByID();
    view.sortSampleByID(model.getListOfNamesOfSamples());
  }

  /**
   * Asks the model to sort tests by duration, then gets a list of names of tests from model
   * and sends to the view to reorder the tests
   */
  @Override
  public void controllerSortTestsByDuration() {
    model.sortTestsByPriority(TestPriority.DURATION);
    view.sortTestByDuration(model.getListOfNamesOfTests());
  }

  /**
   * Asks the model to sort equipment by next service due, then gets a list of names of
   * equipment from model and sends to the view to reorder the equipment
   */
  @Override
  public void controllerSortEquipmentByServiceUrgency() {
    model.sortEquipmentByServiceUrgency();
    view.sortEquipmentByService(model.getListOfNamesOfEquipment());
  }

  /**
   * Asks the model to clear the list of samples, then asks the view to clear the sample
   * display area
   */
  @Override
  public void controllerClearSamples() {
    model.clearSamples();
    view.viewClearSamples();
  }

  /**
   * Asks the model to clear the list of tests, then asks the view to clear the test
   * display area
   */
  @Override
  public void controllerClearTests() {
    model.clearTests();
    view.viewClearTests();
  }

  /**
   * Asks the model to clear the list of equipment, then asks the view to clear the
   * equipment display area
   */
  @Override
  public void controllerClearEquipment() {
    model.clearEquipment();
    view.viewClearEquipment();
  }

  /**
   * Helper method to auto generate samples, tests and equipment.
   * Tells view to autogenerate items (which ideally, it should not). This is
   * for the sake of experimentation.
   */
  @Override
  public void controllerAutogenerate() {
    view.viewAutogenerate();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "add sample" -> controllerAddSample();
      case "deleteSample" -> controllerDeleteSample();
      case "addTestsToSample" -> controllerAddTestsToSample();
      case "showTests" -> controllerShowTests();
      case "add test" -> controllerAddTest();
      case "deleteTest" -> controllerDeleteTest();
      case "showSamples" -> controllerShowSamples();
      case "add equipment" -> controllerAddEquipment();
      case "deleteEquipment" -> controllerDeleteEquipment();
      case "showInfo" -> controllerShowEquipmentInfo();
      case "sort ID" -> controllerSortSamplesByID();
      case "sort duration" -> controllerSortTestsByDuration();
      case "sort service" -> controllerSortEquipmentByServiceUrgency();
      case "clearSamples" -> controllerClearSamples();
      case "clearTests" -> controllerClearTests();
      case "clearEquipment" -> controllerClearEquipment();
      case "autogenerate" -> controllerAutogenerate();
    }
  }

  /**
   * Invoked when an item has been selected or deselected by the user. The code written for this
   * method performs the operations that need to occur when an item is selected (or deselected).
   * For this implementation, nothing is done when an item is selected or deselected.
   *
   * @param e the event to be processed
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
  }
}
