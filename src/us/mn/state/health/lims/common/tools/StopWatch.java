package us.mn.state.health.lims.common.tools;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class StopWatch {

	private Mark start;
	private List<Mark> marks = new ArrayList<Mark>();
	private boolean disable = false;

	/**
	 * Starts the timer.  If the timer is already running it will over-write the start time and message and most
	 * likley make the existing marks meaningless.  To re-use an existing stopwatch  call reset first.
	 *
	 * @param message The header for the series of timings
	 */
	public void start(String message) {
		if (!disable) {
			start = new Mark();
			start.msg = message;
			start.time = System.currentTimeMillis();
		}
	}

	/**
	 * Adds a time marker and a message associated with the marker.
	 *
	 * @param message The message which will be printed with this mark
	 */
	public void setMark(String message) {
		if (!disable) {
			Mark mark = new Mark();
			mark.msg = message;
			mark.time = System.currentTimeMillis();
			marks.add(mark);
		}
	}

	/**
	 * Resets the stopwatch.  Start will need to be called again to start tracking events
	 */
	public void reset() {
		start = null;
		marks = new ArrayList<Mark>();
	}

	/**
	 * Generates a report for the timings of the stop watch.  The header from the start method will be printed
	 * followed by each mark.  Each mark will include the accumulated time since the stopwatch started and the time
	 * since the last mark.
	 */
	public void report() {
		report(System.out);
	}

	/**
	 * @param out
	 */
	public void report(PrintStream out) {
		if (!disable) {
			if (start == null) {
				out.println("Stop watch not started");
				return;
			}
			out.println();
			out.println(start.msg);
			out.println("\taccum\tperiod");
			long lastMark = start.time;

			for (Mark mark : marks) {
				long period = mark.time - lastMark;
				long duration = mark.time - start.time;

				String report = String.format("%s\t%4.3f\t%4.3f",mark.msg, ((float) duration) / 1000.0, ((float) period) / 1000.0);

				out.println(report);

				lastMark = mark.time;
			}
		}
	}

	/**
	 * Disables this stop watch.  Convenient if you want to keep the timer in place but don't want it to be active
	 *
	 *  @param disable True will disable this stopwatch, false will enable it.
	 */
	public void disable(boolean disable) {
		this.disable = disable;
	}

	private class Mark {
		public String msg;
		public long time;
	}
}
