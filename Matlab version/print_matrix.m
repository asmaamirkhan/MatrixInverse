function [ ] = print_matrix( matrix )
    fprintf('\n');
    for i=1:length(matrix)
        for j=1:length(matrix)
            fprintf('%.4f\t\t',matrix(i,j));
        end
        fprintf('\n');
    end
    fprintf('\n');
end
