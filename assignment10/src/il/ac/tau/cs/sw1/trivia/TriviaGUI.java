package il.ac.tau.cs.sw1.trivia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TriviaGUI {

	private static final int MAX_ERRORS = 3;
	private Shell shell;
	private Label scoreLabel;
	private Composite questionPanel;
	private Label startupMessageLabel;
	private Font boldFont;
	private String lastAnswer;
	
	
	// My additions
	private Stack<Map.Entry<String, List<String>>> stack = new Stack<>();
	private int score;
	private int errors;
	private int totalQuestionsAsked;
	String correctAnswer;
	Boolean usedPass;
	Boolean usedFifty;
	Boolean gameOver;
	
	
	// Currently visible UI elements.
	Label instructionLabel;
	Label questionLabel;
	private List<Button> answerButtons = new LinkedList<>();
	private Button passButton;
	private Button fiftyFiftyButton;

	public void open() {
		createShell();
		runApplication();
	}

	/**
	 * Creates the widgets of the application main window
	 */
	private void createShell() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		shell.setText("Trivia");

		// window style
		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width / 3,
				monitor_bounds.height / 4));
		shell.setLayout(new GridLayout());

		FontData fontData = new FontData();
		fontData.setStyle(SWT.BOLD);
		boldFont = new Font(shell.getDisplay(), fontData);

		// create window panels
		createFileLoadingPanel();
		createScorePanel();
		createQuestionPanel();
	}

	/**
	 * Creates the widgets of the form for trivia file selection
	 */
	private void createFileLoadingPanel() {
		final Composite fileSelection = new Composite(shell, SWT.NULL);
		fileSelection.setLayoutData(GUIUtils.createFillGridData(1));
		fileSelection.setLayout(new GridLayout(4, false));

		final Label label = new Label(fileSelection, SWT.NONE);
		label.setText("Enter trivia file path: ");

		// text field to enter the file path
		final Text filePathField = new Text(fileSelection, SWT.SINGLE
				| SWT.BORDER);
		filePathField.setLayoutData(GUIUtils.createFillGridData(1));

		// "Browse" button
		final Button browseButton = new Button(fileSelection, SWT.PUSH);
		browseButton.setText("Browse");
		// "Browse" Listener , using SelectionListener's Class
		browseButton.addSelectionListener(
				new SelectionListener() {
					@Override public void widgetSelected(SelectionEvent e) {
						if (e.getSource() instanceof Button) {
							String path = GUIUtils.getFilePathFromFileDialog(shell);
							filePathField.setText(path);
						}
						
					}
					@Override public void widgetDefaultSelected(SelectionEvent e) {}
				}
				);

		// "Play!" button
		final Button playButton = new Button(fileSelection, SWT.PUSH);
		playButton.setText("Play!");
		// "Play!" Listener
		playButton.addSelectionListener(
				new SelectionListener() {
					@Override public void widgetSelected(SelectionEvent e) {
						if (e.getSource() instanceof Button) { // "Play" has been pressed
							File file = new File(filePathField.getText());
							try {
								Scanner scanner = new Scanner(file);
								while (scanner.hasNextLine()) {
									String currLine = scanner.nextLine();
									String[] currArgs = currLine.split("\t");
									String currQ = currArgs[0]; // Question
									
									List<String> currA = new ArrayList<String>(); // Answers
									for (int i = 1; i < currArgs.length; i++) {
										currA.add(currArgs[i]);
									}
									Map.Entry<String,List<String>> entry =
										    new AbstractMap.SimpleEntry<String, List<String>>(currQ, currA);
									stack.push(entry);
								}
							} catch (FileNotFoundException e1) {
								System.out.println("Invalid file path!");
							}
							
							Collections.shuffle(stack);
							
							// Init relevant values
							lastAnswer = "";
							score = 0;
							usedPass = false;
							usedFifty = false;
							totalQuestionsAsked = 0;
							errors = 0;
							gameOver = false;
							
							// Display first question
							Map.Entry<String,List<String>> firstEntry = stack.pop();
							updateQuestionPanel(firstEntry.getKey(), firstEntry.getValue());
							
							
						}
					}
					@Override public void widgetDefaultSelected(SelectionEvent e) {}
				}
				);
		
	}

	/**
	 * Creates the panel that displays the current score
	 */
	private void createScorePanel() {
		Composite scorePanel = new Composite(shell, SWT.BORDER);
		scorePanel.setLayoutData(GUIUtils.createFillGridData(1));
		scorePanel.setLayout(new GridLayout(2, false));

		final Label label = new Label(scorePanel, SWT.NONE);
		label.setText("Total score: ");

		// The label which displays the score; initially empty
		scoreLabel = new Label(scorePanel, SWT.NONE);
		scoreLabel.setLayoutData(GUIUtils.createFillGridData(1));
	}

	/**
	 * Creates the panel that displays the questions, as soon as the game
	 * starts. See the updateQuestionPanel for creating the question and answer
	 * buttons
	 */
	private void createQuestionPanel() {
		questionPanel = new Composite(shell, SWT.BORDER);
		questionPanel.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		questionPanel.setLayout(new GridLayout(2, true));

		// Initially, only displays a message
		startupMessageLabel = new Label(questionPanel, SWT.NONE);
		startupMessageLabel.setText("No question to display, yet.");
		startupMessageLabel.setLayoutData(GUIUtils.createFillGridData(2));
	}

	/**
	 * Serves to display the question and answer buttons
	 */
	private void updateQuestionPanel(String question, List<String> answers) {

		// Update score
		scoreLabel.setText(""+score);

		// Update TQA
		totalQuestionsAsked++;
		
		// Save current list of answers.
		List<String> currentAnswers = answers;
		
		// Save correct answer and shuffle the answers
		correctAnswer = currentAnswers.get(0);
		Collections.shuffle(answers);
		
		
		// clear the question panel
		Control[] children = questionPanel.getChildren();
		for (Control control : children) {
			control.dispose();
		}

		// create the instruction label
		instructionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		instructionLabel.setText(lastAnswer + "Answer the following question:");
		instructionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the question label
		questionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		questionLabel.setText(question);
		questionLabel.setFont(boldFont);
		questionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the answer buttons
		answerButtons.clear();
		for (int i = 0; i < 4; i++) {
			Button answerButton = new Button(questionPanel, SWT.PUSH | SWT.WRAP);
			answerButton.setText(answers.get(i));
			GridData answerLayoutData = GUIUtils.createFillGridData(1);
			answerLayoutData.verticalAlignment = SWT.FILL;
			answerButton.setLayoutData(answerLayoutData);
			
			answerButtons.add(answerButton);
			// Listener to each button
			answerButton.addSelectionListener(
					new SelectionListener() {
						@Override public void widgetSelected(SelectionEvent e) {
							if (e.getSource() instanceof Button && !gameOver) {
								if (answerButton.getText().equals(correctAnswer)) {
									score += 3;
									errors = 0;
									lastAnswer = "Correct! ";
								} else {
									errors += 1;
									score -= 2;
									lastAnswer = "Wrong... ";
								}
								
								// GameOver 
								if (errors == MAX_ERRORS || stack.isEmpty()) {
									GUIUtils.showInfoDialog(shell, "GAME OVER", 
											"Your final score is "+score+" after "+totalQuestionsAsked+" questions.");
									gameOver = true;
								}
								// Update Board
								Map.Entry<String,List<String>> firstEntry = stack.pop();
								updateQuestionPanel(firstEntry.getKey(), firstEntry.getValue());
							}
							
						}
						@Override public void widgetDefaultSelected(SelectionEvent e) {}
					}
					);
		}

		// create the "Pass" button to skip a question
		passButton = new Button(questionPanel, SWT.PUSH);
		passButton.setText("Pass");
		GridData data = new GridData(GridData.END, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		passButton.setLayoutData(data);
		
		// "Pass" Listener
		passButton.addSelectionListener(
				new SelectionListener() {
					@Override public void widgetSelected(SelectionEvent e) {
						if (e.getSource() instanceof Button) {
							score -= usedPass? 1:0;
							usedPass = true;
							scoreLabel.setText(""+score);

							totalQuestionsAsked--;
							Map.Entry<String,List<String>> firstEntry = stack.pop();
							lastAnswer = "";
							updateQuestionPanel(firstEntry.getKey(), firstEntry.getValue());
							passButton.setEnabled(false);
							

						}
						
					}
					@Override public void widgetDefaultSelected(SelectionEvent e) {}
				}
				);
		
		// create the "50-50" button to show fewer answer options
		fiftyFiftyButton = new Button(questionPanel, SWT.PUSH);
		fiftyFiftyButton.setText("50-50");
		data = new GridData(GridData.BEGINNING, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		fiftyFiftyButton.setLayoutData(data);
		// "50-50" Listener
				fiftyFiftyButton.addSelectionListener(
						new SelectionListener() {
							@Override public void widgetSelected(SelectionEvent e) {
								if (e.getSource() instanceof Button) {
									
									score -= usedFifty? 1:0;
									usedFifty = true;
									scoreLabel.setText(""+score);

									
									// Setting Randomness
									ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(0,1,2,3));
									Collections.shuffle(nums);
									
									int changed = 0;
									for (int num : nums) {
										if (changed < 2 && answerButtons.get(num).getText() != correctAnswer) {
											answerButtons.get(num).setEnabled(false);
											changed++;
										}
									}
									fiftyFiftyButton.setEnabled(false);
									
								}
								
							}
							@Override public void widgetDefaultSelected(SelectionEvent e) {}
						}
						);
		
		// Enable and Disable 'Pass' and '50-50' buttons according to instructions
		if (usedPass && score <= 0) {
			passButton.setEnabled(false);
		}
		if (usedFifty && score <= 0) {
			fiftyFiftyButton.setEnabled(false);
		}

		
		// two operations to make the new widgets display properly
		questionPanel.pack();
		questionPanel.getParent().layout();
	}

	/**
	 * Opens the main window and executes the event loop of the application
	 */
	private void runApplication() {
		shell.open();
		Display display = shell.getDisplay();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		boldFont.dispose();
	}
}
