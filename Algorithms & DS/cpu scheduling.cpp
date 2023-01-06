#include <iostream>
#include <bits/stdc++.h>
#include <algorithm>
using namespace std;
int main()
{
    int n;//no of processes
    cin>>n;
    int a[n];//burst time
    for(int i=0;i<n;i++)//entering burst time values
    {
        cin>>a[i];
    }
    //sort(a,a+n);//*******uncomment for sjf scheduling****** ^_^ :P D
    int w[n],t[n],avgt=0,turt=0;
    w[0]=0;
    for(int i=1;i<n;i++)//calculating waiting time
    {
        w[i]=0;
        for(int j=0;j<i;j++)
        {
            w[i]+=a[j];
        }
    }
    for(int i=0;i<n;i++)//calculating turnaround time
    {
        t[i]=a[i]+w[i];
    
    }
    for(int j=0;j<n;j++)//storing values of waiting time and turnaround time
    {
        avgt+=w[j];
        turt+=t[j];
    }
    cout<<(avgt)/n<<endl;//printing average waiting time
    cout<<(turt)/n<<endl;//printing average turnaround time :P :D
    return 0;
         
}
