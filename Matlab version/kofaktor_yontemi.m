function [] = kofaktor_yontemi( matrix )
    if(round(det(matrix)*10000)/10000~=0)
        denk_s = length(matrix);
        EK=zeros(denk_s,denk_s);
        for i = 1:denk_s
            for j = 1:denk_s
                a = carpan(matrix,i,j,denk_s);
                EK(i,j) = ((-1)^(i+j))*det(a);
            end
        end
        EK=EK';
        ters=1/det(matrix)*EK;
        print_matrix(ters);
    else
        fprintf('Girdiginiz matris tekildir!\n\n');
    end
end