package praktikum9;

public class QuickSort
{
	public static void quicksort(int[] array, final int links, final int rechts)
	{
		int li = links;
		int re = rechts;
		
		int pivot = array[(links + rechts) / 2];
		
		do {
			while(array[li] < pivot) li++;
			while(array[re] > pivot) re--;
			if(li <= re) {
				int x = array[li];
				array[li] = array[re];
				array[re] = x;
				li++;
				re--;
			}
		} while(!(li > re));
		if(links < re) quicksort(array, links, re);
		if(rechts > li) quicksort(array, li, rechts);
	}
}