import java.lang.Math;
public class StudentHashSet
{
  private static int size = 0;
  private static int address = 0;
  private static int removeAddress = 0;
  static Student[] hashSet = new Student[11];

  public static void add(Student s)//linear probing
  {
    ensureCapacity();//maintains the .5 loadfactor
    address = Math.abs(s.hashCode()) % hashSet.length;//figuring out where to put the object in the set by making an address
    if(hashSet[address] == null)//if empty, put object there
    {
      hashSet[address] = s;//adding the object to the table
    }
    else//if spot is already taken, linear probing occurs 
    {
      for(int u = address + 1; u != address; u++)//starts at the spot we just checked for null and ends at same place
      {
        if(u >= hashSet.length)//this creates a circular array since linear probing doesn't stop when the array stops
        {
          u = 0;
        }
        if(hashSet[u] == null)//if empty, put object there
        {
          hashSet[u] = s;//adding the object to the table
          return;//stops the loop
        }
      }
    }
    size++;//incrementing size to make the size method O(1)
  }
  
  public static boolean remove(Student s)//need to rehash everytime we remove cuz linear probing
  {
    removeAddress = Math.abs(s.hashCode()) % hashSet.length;//finding the where the object is in our array
    if(s == hashSet[removeAddress])//checking if we have the right object
    {
      hashSet[removeAddress] = null;//removing the object
      size--;//decrementing size to make the size method O(1)
      return true;//successfully removed the object
    }
    return false;//failed at removing the object
  }
  
  public static void reHash(int removeAt)//method to account for linear probing pushing things down
  {
    Student[] newHashSet = new Student[hashSet.length];
    System.arraycopy(hashSet, 0, newHashSet, 0, removeAt - 1);
    for(int i = removeAt; i < hashSet.length - 1; i++)//starting at the place where we removed 
    {
      if(hashSet[i] == null)//essentially shifting everything 
      {
        add(hashSet[i]);
      }
    }
    hashSet = newHashSet;
  }

  public static boolean contains(Student s)//checking to see if our set works
  {
    for(int i = 0; i < hashSet.length - 1; i++)
    {
      if(s == hashSet[i])
      {
        return true;
      }
    }
    return false;
  }

  public static void ensureCapacity()//if loadfactor is > .5 then we resize
  {
    if(size == hashSet.length/2)//if loadfactor is > .5
    {
      Student[] temp = new Student[hashSet.length * 2 + 1];
      System.arraycopy(hashSet, 0, temp, 0, hashSet.length);
      hashSet = temp;
    }
  }

  public static int size()//returning the size in a O(1) computational complexity method
  {
    return size;
  }
}