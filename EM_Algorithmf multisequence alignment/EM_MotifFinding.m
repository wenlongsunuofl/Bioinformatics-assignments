%%%% Project2 for Introduction to bioinformatics 
%%%% Expectation-Maximization algorithm
%%%% Author: Wenlong Sun, UofL. 2014

function varargout = EM_MotifFinding(varargin)
% EM_MOTIFFINDING MATLAB code for EM_MotifFinding.fig
%      EM_MOTIFFINDING, by itself, creates a new EM_MOTIFFINDING or raises the existing
%      singleton*.
%
%      H = EM_MOTIFFINDING returns the handle to a new EM_MOTIFFINDING or the handle to
%      the existing singleton*.
%
%      EM_MOTIFFINDING('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in EM_MOTIFFINDING.M with the given input arguments.
%
%      EM_MOTIFFINDING('Property','Value',...) creates a new EM_MOTIFFINDING or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before EM_MotifFinding_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to EM_MotifFinding_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help EM_MotifFinding

% Last Modified by GUIDE v2.5 25-Jun-2014 14:22:40

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @EM_MotifFinding_OpeningFcn, ...
                   'gui_OutputFcn',  @EM_MotifFinding_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before EM_MotifFinding is made visible.
function EM_MotifFinding_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to EM_MotifFinding (see VARARGIN)

% Choose default command line output for EM_MotifFinding
handles.output = hObject;
set(handles.MotifFinding,'enable','off');
% Update handles structure
guidata(hObject, handles);

% UIWAIT makes EM_MotifFinding wait for user response (see UIRESUME)
% uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = EM_MotifFinding_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;



function edit2_Callback(hObject, eventdata, handles)
% hObject    handle to edit2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit2 as text
%        str2double(get(hObject,'String')) returns contents of edit2 as a double


% --- Executes during object creation, after setting all properties.
function edit2_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function edit1_Callback(hObject, eventdata, handles)
% hObject    handle to edit1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit1 as text
%        str2double(get(hObject,'String')) returns contents of edit1 as a double


% --- Executes during object creation, after setting all properties.
function edit1_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function edit3_Callback(hObject, eventdata, handles)
% hObject    handle to edit3 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit3 as text
%        str2double(get(hObject,'String')) returns contents of edit3 as a double


% --- Executes during object creation, after setting all properties.
function edit3_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit3 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function edit4_Callback(hObject, eventdata, handles)
% hObject    handle to edit4 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit4 as text
%        str2double(get(hObject,'String')) returns contents of edit4 as a double


% --- Executes during object creation, after setting all properties.
function edit4_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit4 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function edit5_Callback(hObject, eventdata, handles)
% hObject    handle to edit5 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit5 as text
%        str2double(get(hObject,'String')) returns contents of edit5 as a double


% --- Executes during object creation, after setting all properties.
function edit5_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit5 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in MotifFinding.
function MotifFinding_Callback(hObject, eventdata, handles)
% hObject    handle to MotifFinding (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
global New_Seqs;
motiflength = str2double(get(handles.edit3,'string'));
iterations  = str2double(get(handles.edit4,'string'));
pseudocount = str2double(get(handles.edit5,'string'));
initialRandomCount = str2double(get(handles.edit6,'string'));
temp = [];
for i=1:length(New_Seqs)
    temp1 = New_Seqs{i,1};
    temp = [temp temp1];
end
[Symb] = unique(temp);
Total_A = length(find(Symb(1)==temp)==1);
Total_C = length(find(Symb(2)==temp)==1);
Total_G = length(find(Symb(3)==temp)==1);
Total_T = length(find(Symb(4)==temp)==1);
clear temp temp1


%%% 
fid =  fopen('output.txt','w');
h = waitbar(0, 'Initializing waitbar...');
for s =1:initialRandomCount
    %%% Initilize start position for each sequence
    for i=1:length(New_Seqs)
        temp = New_Seqs(i);
        position(i) = randi([1,length(temp{1})-motiflength+1],[1,1]);
    end
    clear temp
    u = num2str(s);
    perc = (s/initialRandomCount)*100;
    waitbar(perc/100,h,sprintf('%d%% along...',perc))
    for k=1:iterations
        for i=1:length(New_Seqs)
            temp = New_Seqs(i);
            temp2 = temp{1};
            residue{i,1} = temp2(position(i):position(i)+motiflength-1);
        end
        for i=1:length(residue)
            temp3 = residue{i,1};
            for j=1:length(temp3)
                if strcmp(temp3(j),'A')
                    residuem(i,j) = 0;
                elseif strcmp(temp3(j),'C')
                    residuem(i,j) = 1;
                elseif strcmp(temp3(j),'G')
                    residuem(i,j) = 2;
                else
                    residuem(i,j) = 3;
                end
            end
        end
        clear temp1 temp2 temp3
        %%%expectation      
        residuematrix = zeros(4,motiflength+1);
        temp1 = 0;
        for i=1:4
            for j=2:motiflength+1
                flag = find(residuem(:,j-1)==temp1);
                if ~isempty(flag)
                    residuematrix(i,j) = length(find(residuem(:,j-1)==temp1));
                else
                    residuematrix(i,j) = 0;
                end
            end
            temp1 = temp1+1;
        end
        %%% background
        temp2 = sum(residuematrix,2);
        residuematrix(1,1) = Total_A-temp2(1);
        residuematrix(2,1) = Total_C-temp2(2);
        residuematrix(3,1) = Total_G-temp2(3);
        residuematrix(4,1) = Total_T-temp2(4);
        %%% calculate frequency and log value (apply pseudocount)
        for i=1:4
            for j=2:size(residuematrix,2)
                frequency(i,j) = (residuematrix(i,j)+1)/(sum(residuematrix(:,j))+4*pseudocount);
            end
            frequency(i,1) = (residuematrix(i,1))/(sum(residuematrix(:,1))+4*pseudocount);
        end
        for i=1:4
            for j=1:size(frequency,2)-1
                logodd(i,j) = log2(frequency(i,j+1)/frequency(i,1));
            end
        end

        %%% maximization
        for i=1:length(New_Seqs)
            temp3 = New_Seqs{i};
            for j=1:length(temp3)-motiflength+1
                temp4 = temp3(j:j+motiflength-1);
                probability_sum = 0;
                for s = 1:length(temp4)
                    if strcmp(temp4(s),'A')
                        probability_sum = probability_sum+logodd(1,s);
                    elseif strcmp(temp4(s),'C')
                        probability_sum = probability_sum+logodd(2,s);
                    elseif strcmp(temp4(s),'G')
                        probability_sum = probability_sum+logodd(3,s);
                    else
                        probability_sum = probability_sum+logodd(4,s);
                    end
                end
                temp5(j) = probability_sum;
              
            end
            [temp6 temp7] = sort(temp5);
            maxlog(i)   = temp6(length(temp7));
            position(i) = temp7(length(temp7));
            BestMotif{i,1} = temp3(position(i):position(i)+motiflength-1);
            clear temp3 temp4 temp5 temp6 temp7
        end
        
    end
    for i=1:length(New_Seqs)
        MaxLog{i,1} = num2str(maxlog(i));
        PositionValue{i,1} = num2str(position(i));
        SeqsNumber {i,1} = num2str(i);
        Total{i,1} = strcat(SeqsNumber{i,1},'---',PositionValue{i,1},'---',BestMotif{i,1},'---',MaxLog{i,1});  
    end
%     Total = [ SeqsNumber PositionValue BestMotif MaxLog];
    clear SeqsNumber PositionValue BestMotif MaxLog
    fprintf(fid, ['Initial Random Alignment Run number: ', u, '\n']);
    fprintf(fid,['Table columns: Sequence number, Position, Best Motif, Max-logodd' '\n\n']);
    for i=1:size(Total,1)
%         fprintf(fid,formatSpec,Total{i,:});
        fprintf(fid,Total{i,:});
        fprintf(fid, '\n');
    end
    fprintf(fid, '\n\n');
    
    %%% print residue matrix
    fprintf(fid, ['Residue Matrix:','\n']);
    
    for i=1:size(residuematrix,1)
        temp3 = [];
        temp1 = residuematrix(i,:);
        for j=1: length(temp1)
            temp2 = num2str(temp1(j));
            temp3 = strcat(temp3,temp2,'---');
        end
        if i==1
            fprintf(fid,['A:',temp3]);
        elseif i==2
            fprintf(fid,['C:',temp3]);
        elseif i==3
            fprintf(fid,['G:',temp3]);
        else
            fprintf(fid,['T:',temp3]);
        end
        fprintf(fid, '\n');
    end
    clear temp1 temp2 temp3
    fprintf(fid, '\n\n');
    clear Total
end
fclose(fid);
close(h)

%%% output to screen
temp1 = [];
for i=1:length(New_Seqs)
    temp = New_Seqs{i,1};
    if position(i)+motiflength>length(temp)
        temp1 = strcat(temp(1:position(i)-1),'{',temp(position(i):position(i)+motiflength-1),'}');
    else
        temp1 = strcat(temp(1:position(i)-1),'{',temp(position(i):position(i)+motiflength-1),'}',temp(position(i)+motiflength:end));
    end
    result{i,1} = strcat('> Sequence ',num2str(i),':',' ',temp1);
end
set(handles.edit2,'string',result);

% --- Executes on button press in pushbutton2.
function pushbutton2_Callback(hObject, eventdata, handles)
% hObject    handle to pushbutton2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
global New_Seqs;
filename = uigetfile({'.fa'},'Select a file');
if filename ~=0
    Temp = fopen(filename);
    tline = textscan(Temp,'%s');
    fclose(Temp);
    count = 0;
    temp1 = tline{1,1};
    temp3 = [];
    for i=1:length(temp1)
        temp2 = temp1{i,1};
        if strcmp(temp2(1),'A') || strcmp(temp2(1),'C') || ...
                strcmp(temp2(1),'G') || strcmp(temp2(1),'T')
            count = count+1;
            New_Seqs {count,1} = temp2;
            temp3 = [temp3 temp2];
        end
    end
    set(handles.edit1,'string',temp1);
else
    errordlg('Wrong File!');
    return;
end
set(handles.MotifFinding,'enable','on');



function edit6_Callback(hObject, eventdata, handles)
% hObject    handle to edit6 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit6 as text
%        str2double(get(hObject,'String')) returns contents of edit6 as a double


% --- Executes during object creation, after setting all properties.
function edit6_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit6 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end
