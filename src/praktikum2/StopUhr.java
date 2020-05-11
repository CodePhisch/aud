package praktikum2;

public class StopUhr
{
	private long startTime, stopTime;

	public void start()
	{
		startTime = System.nanoTime();
	}

	public void stop()
	{
		stopTime = System.nanoTime();
	}

	public String getDuration()
	{
		return String.format("%.6f", (double)(stopTime - startTime) / 1000000000.0);
	}
}