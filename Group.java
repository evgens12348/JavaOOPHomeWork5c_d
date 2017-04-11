package com.gmail.s12348.evgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Group implements Voencom {
	private Student[] studentGroup = new Student[10];

	public Group() {
	}

	public void enterStudent() {
		for (;;) {
			int s = JOptionPane.showConfirmDialog(null, "Want to enter a new student into the group?");
			if (s == 0) {

				zanesenieStudent(proverkaGroup());

			} else {
				break;
			}
		}
	}

	public int proverkaGroup() {	/*New metod*/
		int i = 0;
		for (i = 0; i < studentGroup.length;) {
			if (studentGroup[i] != null) {
				i += 1;
			} else {
				break;
			}

		}
		return i;
	}

	public void zanesenieStudent(int i) {
		try {
			if (i == studentGroup.length) {
				resizeArray();
			}
			studentGroup[i] = new Student();
			studentGroup[i].inputSurname();
			studentGroup[i].inputName();
			studentGroup[i].inputSex();
			studentGroup[i].inputOld();
			studentGroup[i].inputHighSchool();
			studentGroup[i].inputDepartment();
			studentGroup[i].inputKurs();
			studentGroup[i].inputType();
			studentGroup[i].inputAssessment();
		} catch (MyExeption e) {
			e.nameNull("null");
		}
	}

	public void resizeArray() {
		Student[] temp = new Student[studentGroup.length + 1];
		System.arraycopy(studentGroup, 0, temp, 0, studentGroup.length);
		studentGroup = temp;
	}

	public void requestDeleteStudent() {
		for (;;) {
			try {
				int n = Integer.valueOf(JOptionPane.showInputDialog(
						"Enter the number of the record you want to delete (From 1 to " + studentGroup.length + ")."));
				if (n < 1 & n > studentGroup.length)
					throw new MyNegativeExeption();
				deleteStudent(n);
				break;
			} catch (MyNegativeExeption e) {
				e.negativeNumber();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error number format");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Canseled set as default");
				break;
			}
		}

	}

	public void deleteStudent(int n) {
		studentGroup[n - 1] = null;
	}

	public String zaprosPoiskStudent() {
		String surname = "";
		for (;;) {
			try {
				surname = String
						.valueOf(JOptionPane.showInputDialog("Enter the surname of the student you want to find."));
				if (surname == "null")
					throw new MyExeption();
				if (checkString(surname) != true) {
					JOptionPane.showMessageDialog(null, "Input Error");
				} else {
					break;
				}
			} catch (MyExeption e) {
				e.nameNull("null");
			}
		}
		return surname;
	}

	public void poiskStudent(String surname) {
		int k = 0;
		for (int i = 0; i < studentGroup.length; i++) {
			if (studentGroup[i] != null) {
				if (studentGroup[i].getSurname().compareToIgnoreCase(surname) == 0) {
					System.out.println(studentGroup[i]);
					System.out.println();
					k += 1;
				}
			}
		}
		if (k == 0) {
			JOptionPane.showMessageDialog(null, "Нет такого студента");
		}
	}

	public void sortArrayByName() {
		Student temp = new Student();
		for (int i = 0; i < studentGroup.length; i++) {
			for (int j = studentGroup.length - 1; j > i; j--) {
				if (studentGroup[j] != null & studentGroup[j - 1] != null) {
					if (studentGroup[j].getName().compareToIgnoreCase(studentGroup[j - 1].getName()) < 0) {
						temp = studentGroup[j];
						studentGroup[j] = studentGroup[j - 1];
						studentGroup[j - 1] = temp;
					}
				}
			}
		}
	}

	public void sortArrayBySurname() {
		Student temp = new Student();
		for (int i = 0; i < studentGroup.length; i++) {
			for (int j = studentGroup.length - 1; j > i; j--) {
				if (studentGroup[j] != null & studentGroup[j - 1] != null) {
					if (studentGroup[j].getSurname().compareToIgnoreCase(studentGroup[j - 1].getSurname()) < 0) {
						temp = studentGroup[j];
						studentGroup[j] = studentGroup[j - 1];
						studentGroup[j - 1] = temp;
					}
				}
			}
		}
	}

	public void sortArrayBySex() {
		Student temp = new Student();
		for (int i = 0; i < studentGroup.length; i++) {
			for (int j = studentGroup.length - 1; j > i; j--) {
				if (studentGroup[j] != null & studentGroup[j - 1] != null) {
					if (studentGroup[j].getSex().compareToIgnoreCase(studentGroup[j - 1].getSex()) < 0) {
						temp = studentGroup[j];
						studentGroup[j] = studentGroup[j - 1];
						studentGroup[j - 1] = temp;
					}
				}
			}
		}
	}

	public void sortArrayByAge() {
		Student temp = new Student();
		for (int i = 0; i < studentGroup.length; i++) {
			for (int j = studentGroup.length - 1; j > i; j--) {
				if (studentGroup[j] != null & studentGroup[j - 1] != null) {
					if (studentGroup[j].getOld() < studentGroup[j - 1].getOld()) {
						temp = studentGroup[j];
						studentGroup[j] = studentGroup[j - 1];
						studentGroup[j - 1] = temp;
					}
				}
			}
		}
	}

	public void sortArray() {
		int n = 0;
		for (;;) {
			try {
				n = Integer.valueOf(
						JOptionPane.showInputDialog("Select by what to sort (1-name, 2-surname, 3-sex, 4-age)."));
				if (n < 1 & n > 4)
					throw new MyNegativeExeption();
				break;
			} catch (MyNegativeExeption e) {
				e.negativeNumber();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error number format");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Canseled set as default");
				break;
			}
		}
		switch (n) {
		case 1:
			sortArrayByName();
			break;
		case 2:
			sortArrayBySurname();
			break;
		case 3:
			sortArrayBySex();
			break;
		case 4:
			sortArrayByAge();
			break;
		}
	}

	public boolean checkString(String string) {
		if (string.length() == 0) {
			return false;
		} else {
			Pattern p = Pattern.compile("^([a-zA-Z- №]+)");
			Matcher m = p.matcher(string);

			return m.matches();
		}
	}

	public void writeToFile() {	/*New metod*/
		File fileOne = new File("Group.txt");

		try (PrintWriter prwOne = new PrintWriter(fileOne)) {
			if (fileOne.exists() == false) {
				fileOne.createNewFile();
			}
			for (int i = 0; i < studentGroup.length; i++) {
				if (studentGroup[i] != null) {
					prwOne.print(studentGroup[i].getName() + " ");
					prwOne.print(studentGroup[i].getSurname() + " ");
					prwOne.print(studentGroup[i].getSex() + " ");
					prwOne.print(studentGroup[i].getOld() + " ");
					prwOne.print(studentGroup[i].getHighSchool() + " ");
					prwOne.print(studentGroup[i].getDepartment() + " ");
					prwOne.print(studentGroup[i].getKurs() + " ");
					prwOne.print(studentGroup[i].getType() + " ");
					prwOne.print(studentGroup[i].getAssessment() + " ");
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void readToFile() {	/*New metod*/
		try (BufferedReader bfrOne = new BufferedReader(new FileReader("Group.txt"))) {
			String[] masOne = bfrOne.readLine().split("[ ]");
			for (int j = 0; j < masOne.length;) {
				for (int i = proverkaGroup(); i < masOne.length / 9;) {
					studentGroup[i] = new Student();
					studentGroup[i].setName(masOne[j]);
					studentGroup[i].setSurname(masOne[j + 1]);
					studentGroup[i].setSex(masOne[j + 2]);
					studentGroup[i].setOld(Integer.parseInt(masOne[j + 3]));
					studentGroup[i].setHighSchool(masOne[j + 4]);
					studentGroup[i].setDepartment(masOne[j + 5]);
					studentGroup[i].setKurs(Integer.parseInt(masOne[j + 6]));
					studentGroup[i].setType(masOne[j + 7]);
					studentGroup[i].setAssessment(Double.parseDouble(masOne[j + 8]));
					j += 9;
					break;
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (NullPointerException e) {

		}

	}

	@Override
	public String toString() {
		int k = 0;
		for (int i = 0; i < studentGroup.length; i++) {
			if (studentGroup[i] != null) {
				System.out.println(studentGroup[i] + " ");
				k += 1;
			}
		}

		return "In Group [" + k + "]";
	}

	@Override
	public void prizivnik() {
		Student[] prizivnik = new Student[studentGroup.length];
		for (int i = 0; i < studentGroup.length; i++) {
			if (studentGroup[i] != null) {
				if (studentGroup[i].getOld() > 18 & studentGroup[i].getSex().compareTo("man") == 0) {
					for (int j = 0; j < prizivnik.length; j++) {
						if (prizivnik[j] == null) {
							prizivnik[j] = studentGroup[i];
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < prizivnik.length; i++) {
			if (prizivnik[i] != null) {
				System.out.println(prizivnik[i]);
			}
		}
	}

}
