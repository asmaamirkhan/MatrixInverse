clear all; clc;
A=[1 0 2; 6 1 0; 3 2 1]
% b=[1 5 7 -6];
% [value, index] = min(b)

[value, index]=max(abs(A(:,1)))
temp=A(1,:);
A(1,:)=A(index,:);
A(index,:)=temp;
A