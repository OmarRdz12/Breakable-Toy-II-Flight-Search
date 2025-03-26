import React, { useMemo, useRef, useState } from 'react';
import { Select, Spin } from 'antd';
import type { SelectProps } from 'antd';
import debounce from 'lodash.debounce'
import { BasicSelect } from '../../interfaces/types';

interface InputDebounceSelectProps {
  label: string,
  id: string,
  fetchData: (search: string) => Promise<BasicSelect[]>
  value: BasicSelect[]
  setValue: (newValue: BasicSelect[]) => void
}

export interface DebounceSelectProps<ValueType = any>
  extends Omit<SelectProps<ValueType | ValueType[]>, 'options' | 'children'> {
  fetchOptions: (search: string) => Promise<ValueType[]>;
  debounceTimeout?: number;
}

function DebounceSelect<
  ValueType extends { key?: string; label: React.ReactNode; value: string | number } = any,
>({ fetchOptions, debounceTimeout = 800, ...props }: DebounceSelectProps<ValueType>) {
  const [fetching, setFetching] = useState(false);
  const [options, setOptions] = useState<ValueType[]>([]);
  const fetchRef = useRef(0);

  const debounceFetcher = useMemo(() => {
    const loadOptions = (value: string) => {
      fetchRef.current += 1;
      const fetchId = fetchRef.current;
      setOptions([]);
      setFetching(true);

      fetchOptions(value).then((newOptions) => {
        if (fetchId !== fetchRef.current) {
          return;
        }

        setOptions(newOptions);
        setFetching(false);
      });
    };

    return debounce(loadOptions, debounceTimeout);
  }, [fetchOptions, debounceTimeout]);

  return (
    <Select
      labelInValue
      filterOption={false}
      onSearch={debounceFetcher}
      notFoundContent={fetching ? <Spin size="small" /> : null}
      {...props}
      options={options}
    />
  );
}

const InputDebounceSelect = ({
  label,
  id,
  fetchData,
  value,
  setValue
}: InputDebounceSelectProps) => {

  return (
    <div className="flex gap-4 mt-4 items-start justify-start w-full">
      <label htmlFor={id} className="w-1/4"> {label}</label>
      <DebounceSelect
        maxCount={1}
        mode="multiple"
        value={value}
        fetchOptions={fetchData}
        style={{ width: '50%' }}
        size="large"
        onChange={(newValue) => {
          if (Array.isArray(newValue)) {
            setValue(newValue);
          }
        }}
      />
    </div>

  );
};

export default InputDebounceSelect;