#include <iostream>
using namespace std;
int swap(int &a,int &b)
{
    int temp;
    temp=a;
    a=b;
    b=temp;
    return 0;
}
int heapify(int arr[], int n, int i)
{
   int largest = i;
   int l = 2*i + 1;
   int r = 2*i + 2;

   if (l < n && arr[l] > arr[largest])
     largest = l;

   if (r < n && arr[r] > arr[largest])
     largest = r;

   if (largest != i)
   {
     swap(arr[i], arr[largest]);

     // Recursively heapify the affected sub-tree
     heapify(arr, n, largest);
   }
   return 0;
}
int heapSort(int arr[], int n)
{
   // Build max heap
   for (int i = n / 2 - 1; i >= 0; i--)
     heapify(arr, n, i);

   // Heap sort
   for (int i=n-1; i>=0; i--)
   {
     swap(arr[0], arr[i]);
     
     // Heapify root element to get highest element at root again
     heapify(arr, i, 0);
   }
   return 0;
}

m
int main() {
	
    int a[1000],n,i;
    cout<<"enter n value"<<endl;
    cin>>n;
    cout<<"enter n number of integers"<<endl;
    for(i=0;i<n;i++)
    {
        cin>>a[i];
    }
   heapSort(a,n);
   cout<<"elements after sorting"<<endl;
   for(i=0;i<n;i++)
   {
       cout<<a[i]<<endl;
   }
    return 0;
}
