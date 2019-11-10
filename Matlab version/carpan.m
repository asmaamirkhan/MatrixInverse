function [ snc ] = carpan(mat, satir, sutun, denk)
    m = 1;
    n = 1;
    snc = zeros(denk-1,denk-1);
        for i = 1:denk
            for j = 1:denk
                if(i ~= satir && j ~= sutun)
                    snc(m, n) = mat(i, j);
                    n = n + 1;    
                end
            end
            if( i ~= satir)
                m = m + 1; 
            end
            n = 1;
        end
end