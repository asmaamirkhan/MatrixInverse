function [ sing ] = isSingular( matrix, m)
    zero = 0;
    sifir = 0;
    sing = 0;
    for i = 1:m
        for j = 1:m
            if(matrix(i, j) == 0)
                zero = zero + 1;
            end
            if(matrix(j, i) == 0)
                sifir = sifir + 1;
            end
        end
        if(zero == m || sifir == m)
            sing = 1;
            break;
        end
        zero = 0;
        sifir = 0;
    end
end
