function [matrix] = pivotlama( matrix, m, row)
    index = row;
    max = matrix(row, row);
    for i = row:m
        if(abs(matrix(i, row)) > abs(max))
            max = matrix(i, row);
            index = i;
        end
    end
    temp = matrix(row, :);
    matrix(row, :) = matrix(index, :);
    matrix(index, :) = temp;
end