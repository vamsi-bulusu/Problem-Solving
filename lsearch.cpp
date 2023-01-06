#include <iostream>
using namespace std;
int lsearch(int a[],int n,int key)
{
    int i;
    for(i=0;i<n;i++)
    {
        if(a[i]==key)
        {
            return i;
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
   pos=lsearch(a,n,key);
   if(pos==-1)
   {
       cout<<"element not found"<<endl;
   }
   else
   cout<<"element found at index:"<<pos<<" "<<"and location:"<<pos+1<<endl;
    return 0;
}
