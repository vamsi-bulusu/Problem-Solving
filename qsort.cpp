#include <iostream>
using namespace std;
int partition(int a[],int start,int end)
{
    int pindex,i,pivot,temp,temp1;
    pindex=start;
    pivot=a[end];
    for(i=start;i<end-1;i++)
    {
        if(a[i]<=pivot)
        {
            temp=a[i];
            a[i]=a[pindex];
            a[pindex]=temp;
            pindex++;
        }
    }
        temp1=a[pindex];
        a[pindex]=a[end];
        a[end]=temp1;
    
    return pindex;
}
int qsort(int a[],int start,int end)
{
    int pindex;
    if(start<=end)
    {
        
    pindex=partition(a,start,end);
    qsort(a,start,pindex-1);
    qsort(a,pindex+1,end);
    }
    return 0;
}

int main() {
	
    int a[1000],n,i,start,end,pindex,pivot;
    cout<<"enter n value"<<endl;
    cin>>n;
    start=0;
    end=n-1;
    cout<<"enter n number of integers"<<endl;
    for(i=0;i<n;i++)
    {
        cin>>a[i];
    }
    qsort(a,start,end);
    cout<<"after soting"<<endl;
    for(i=0;i<n;i++)
    {
        cout<<a[i]<<endl;
    }
    return 0;
}
