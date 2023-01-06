#include <iostream>
using namespace std;
int bsearch(int a[],int n,int key)
{
    int low=0;
    int high=n-1;
    int mid;
    while(low<=high)
    {
      mid=(low + high)/2;
      if(key==a[mid])
      {
          return mid;
      }
      else if(key > a[mid])
      {
          low=mid+1;
      }
      else
      {
          high=mid-1;
      }
    }
    return -1;
}

int main() {
	
    int a[1000],n,i,key,pos;
    cout<<"enter n value"<<endl;
    cin>>n;
    cout<<"enter n number of integers"<<endl;
    for(i=0;i<n;i++)
    {
        cin>>a[i];
    }
    cout<<"Element to be searched"<<endl;
    cin>>key;
   pos=bsearch(a,n,key);
   if(pos==-1)
   {
       cout<<"element not found"<<endl;
   }
   else
   cout<<"element found at index:"<<pos<<" "<<"and location:"<<pos+1<<endl;
    return 0;
}
