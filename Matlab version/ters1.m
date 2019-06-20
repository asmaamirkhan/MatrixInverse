clear all; clc;
% A=[1 2 3; 1 0 1; 3 4 7];
% gauss_yontemi(A)
% % Matlab bu matrisi yanlis hesapliyor!
% print_matrix(inv(A))
while(1==1)
    option = input('1 veya 2 girin:\n1- Rastgele matris olusturulsun\n2- Bir matris girmek istiyorum\n');       
    if(option == 1 || option == 2)
        denk = input('Matris boyutunu girin: ');
        break;
    else
        fprintf('Yanlis giris!\nTekrar deneyin!\n');
    end
end
if(option==1)
    A=randi(50,denk,denk);
else
    A=zeros(denk, denk);
    for i=1:denk
        for j=1:denk
            fprintf('A(%d,%d) = ',i,j);
            A(i,j)=input('');
        end
    end
end
fprintf('Asil Matris:\n');
print_matrix(A);
while(1==1)
    option=input('Yontem secin:\n1- Gauss yontemi\n2- Kofaktor yontemi\n3- Ikisini denemek istiyorum, bir de Matlab sonucu yazdirilsin\n');
    if(option == 1)
        fprintf('Gauss Yontemi:\n');
        gauss_yontemi(A)
        break;
    elseif(option == 2)
        fprintf('\nKofaktor Yontemi:\n');
        kofaktor_yontemi(A);
        break;
    elseif(option == 3)
        fprintf('\nGauss Yontemi:\n');
        gauss_yontemi(A)
        fprintf('\nKofaktor Yontemi:\n');
        kofaktor_yontemi(A)
        fprintf('\nMatlab sonucu:\n');
        print_matrix(inv(A));
        break;
    else
        fprintf('Yanlis giris!\nTekrar deneyin!\n');
    end
end
