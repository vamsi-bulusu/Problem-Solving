#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    int n;
    cin>>n;
    int i1,j1,i2,j2;
    cin>>i1>>j1>>i2>>j2;
    int count=0,flag=0;
    string s;
    while(i1!=i2 && j1!=j2)
    {
        if(i1>=i2 && j1>=j2)//ul
        {
             i1=i1-2;
             j1=j1-1;
             count=count+1;
             s=s+" " + "UL";
             flag=1;
        }
        else if(i1<=i2 && j1<=j2)//ur
        {
            i1=i1+2;
            j1=j1+1;
            count=count+1;
            s=s+" " + "UR";
             flag=1;
        }
        else if(j1<j2)//r
        {
            j1=j1+2;
            count=count+1;
            s=s+" " + "R";
            flag=1;
        }
        else if(j2<j1)//l
        {
            j1=j1-2;
            count=count+1;
            s=s+" " + "L";
             flag=1;
        }
        else if(i1<=i2 && j1>=j2)//ll
        {
            i1=i1+2;
            j1=j1-1;
            count=count+1;
            s=s+" " + "LL";
            flag=1;
        }
        else if(i1<=i2 && j1<=j2)//lr
        {
            i1=i1+2;
            j1=j1+1;
            count=count+1;
            s=s+" " + "LR";
            flag=1;
        }
        else
        {
            if(i2>n || j2>n && i1!=i2 && j1!=j2)
            {     
            flag=0;
            cout<<"impossible"<<endl;
            break;
            }
        }
    }
    if(flag==1)
    {
    cout<<count<<endl;
    cout<<s<<" ";
    } 
    return 0;
}
