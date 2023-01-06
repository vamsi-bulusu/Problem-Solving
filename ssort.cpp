#include <iostream>
using namespace std;
int ssort(int a[],int n)
{
    int imin,i,temp,j;
    for(i=0;i<n-1;i++)
    {
        imin=i;
        for(j=i+1;j<n;j++)
        {
            if(a[j]<a[imin])
            {
                imin=j;
            }
        }
            temp=a[i];
            a[i]=a[imin];
            a[imin]=temp;
        
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
    ssort(a,n);
    cout<<"after soting"<<endl;
    for(i=0;i<n;i++)
    {
        cout<<a[i]<<endl;
    }
    return 0;
}
