package controller;
import model.*;
import java.util.ArrayList;
import dao.*;
public class LogController {
	public ArrayList<Logs> listarlogs() throws ExceptionDao{
		return new LogDao().listarlogs();
	}
	public ArrayList<Logs> listarlogs(String filtro) throws ExceptionDao{
		return new LogDao().listarlogs(filtro);
	}
}