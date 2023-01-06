#include <iostream>
using namespace std;
int isort(int a[],int n)
{
    int value,hole,i;
    for(i=1;i<=n-1;i++)
    {
        value=a[i];
        hole=i;
        while(hole>0 && a[hole-1]>value)
        {
            a[hole]=a[hole-1];
            hole--;
        }
        a[hole]=value;
    }
    return 0;
}

int main() {
	
    int a[1000],n,i;
    cout<<"enter n value"<<endl;
    cin>>n;
    cout<<"enter n number of integers"<<endl;
    for(i=0;i<n;i++)
    {
        cin>>a[i];
    }
    isort(a,n);
    cout<<"after soting"<<endl;
    for(i=0;i<n;i++)
    {
        cout<<a[i]<<endl;
    }
    return 0;
}
