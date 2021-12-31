public class MathHelper 
{
    public static float ArrayMax(float arr[])
    {
        return SortArray(arr)[arr.length - 1];
    }

    public static float ArrayMin(float arr[])
    {
        return SortArray(arr)[0];
    }

    public static float Distance(Vector2D pointA, Vector2D pointB)
    {
        return (float)Math.sqrt(Math.pow((double)(pointB.x - pointA.x), 2) + Math.pow((double)(pointB.y - pointA.y), 2));
    }

    public static int GetArrayIndex(float arr[], float f)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == f);
                return i;
        }

        return -1;
    }

    public static float[] SortArray(float arr[])
    {
        float temp;

        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length; j++)
            {
                if (arr[i] > arr[j])
                {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }
}
