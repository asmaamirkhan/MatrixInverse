function [] = gauss_yontemi( matrix )
    denk_s = length(matrix);
    gauss = [matrix eye(denk_s)];
    sing=0;
    for j=1:denk_s
        gauss=pivotlama(gauss, denk_s, j);
        for i=j:denk_s
            div = gauss(i,j);
            if(div~=0)
                gauss(i,:) = gauss(i,:)/div;
            
                if(i~=j && div~=0)
                    gauss(i,:) = gauss(i,:)-gauss(j,:);
                end
            end
            if(isSingular(round(gauss*10000)/10000, denk_s)==1)
                sing=1;
                break;
            end
        end
    end
    if(sing==0)
        for j=denk_s:-1:1
            for i=j:-1:1
                div = gauss(i,j);
                if(div~=0)
                    gauss(i,:)=gauss(i,:)/div;
                end
                if(i~=j && div~=0)
                    gauss(i,:)=gauss(i,:)-gauss(j,:);
                end
                if(isSingular(round(gauss*10000)/10000, denk_s)==1)
                    sing=1;
                    break;
                end
            end
        end
        gauss=gauss(:,denk_s+1:denk_s*2);
        if(sing==0)
            print_matrix(gauss);
        end
    end
    if(sing==1)
        fprintf('Girdiginiz matris tekildir!\n\n');
    end
    end

