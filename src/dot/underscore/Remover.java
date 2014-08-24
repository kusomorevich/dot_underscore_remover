package dot.underscore;

import java.io.File;

public class Remover {

	
	
	public static void main(String[] args) {
		if (args == null || args.length < 1 ) {
			System.err.println("Param Error.");
			return;
		}
		String routeDir = args[0];
		File route = new File(routeDir);
		if (!route.exists() || !route.isDirectory()) {
			System.err.println("Param Error.");
			return;
		}
		new Remover().delAllDotUnderscores(route);
		System.out.println("Fin.");
	}

	protected void delAllDotUnderscores(File dir) {
		File[] files = dir.listFiles();
		for (File file : files) {
			String name = file.getName();
			if (name.matches("^\\._.*")) {
				file.delete();
				log(String.format("delete %s.", file.getAbsolutePath()));
				continue;
			}
			if (file.isDirectory()) {
				this.delAllDotUnderscores(file);
			}
		}
	}
	
	protected void log(String text) {
		System.out.println(text);
	}
}
