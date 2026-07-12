package controller;
import model.*;
import java.util.ArrayList;
import dao.*;
public class LogController {
	public ArrayList<Logs> listarlogs() throws ExceptionDao{
		return new Logs().listarlogs();
		
	}
}
